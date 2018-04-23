/*
Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
CS341 Lab 3 - Cache Lab
Cache Simulator
*/

#include "cachelab.h"
#include <stdio.h>
#include <unistd.h>
#include <getopt.h>
#include <stdlib.h>

struct CacheStats {
  int hits;
  int misses;
  int evictions;
};

struct CacheParams {
  int s;
  int e;
  int b;
  char* traceFile;
  int verbose;
};

struct Cache {
  struct CacheSet* sets;
};

struct CacheSet {
  struct CacheLine* lines;
};

struct CacheLine {
  char valid;
  long tag;
  int time;
};


struct CacheParams captureOptions(int argc, char **argv);
struct CacheStats processTraceFile(char* traceFile, struct Cache cache, struct CacheParams cacheParams, struct CacheStats stats, int isVerbose);
int processOp(struct Cache cache, unsigned long long int address, struct CacheParams cacheParams);
int checkForEmpty(struct CacheSet set, int e);
int evictLine(struct CacheSet set, int e);
struct Cache makeCache(struct CacheParams cacheParams);


struct CacheParams captureOptions(int argc, char **argv)
{
  
  struct CacheParams cacheParams;

  int isVerbose = 0;
  int c;

  char* helpString = "Cache Simulator Program. Avaialble options are:\n"
               "-h Help\n"
               "-v Verbosity\n"
               "-s Set index bits\n"
               "-E Associativity (number of lines per set)\n"
               "-b Number of block bits\n"
               "-t Trace File Location.\n";

  while ((c = getopt (argc, argv, "h::v::s:E:b:t:")) != -1)
  {
    switch(c)
    {
      case 'h':
        printf("%s", helpString);
        exit(0);
      case 's':
        cacheParams.s = atoi(optarg);
        break;
      case 'E':
        cacheParams.e = atoi(optarg);
        break;
      case 'b':
        cacheParams.b = atoi(optarg);
        break;
      case 't':
        cacheParams.traceFile = optarg;
        break;
      case 'v':
        isVerbose = 1;
        cacheParams.verbose = 1;
        break;
      default:
        printf("Invalid argument type.\n");
        exit(0);
    }

  }

  printf("Here's the options you told me to use:\n");
  printf("Verbose:%d s:%d E:%d b:%d Trace File:%s\n\n", isVerbose, cacheParams.s, cacheParams.e, cacheParams.b, cacheParams.traceFile);
  
  return cacheParams;
}



// Reading the file's contents
struct CacheStats processTraceFile(char* traceFile, struct Cache cache, struct CacheParams cacheParams, struct CacheStats stats, int isVerbose)
{
  FILE* file = fopen(traceFile, "r");
  char operation = 0;
  unsigned address = 0;
  int size = 0;

  // This won't read the I instructions as they don't start with a space.
  // This is intended behavior.
  if (file != NULL)
  { 
    while (fscanf(file, " %c %x,%d", &operation, &address, &size) > 0)
    {
      // We don't want to process any "I" operations...
      if(operation != 'I')
      {
          
        int stat1 = -1;
        int stat2 = -1;
        char* string1 = "";
        char* string2 = "";

        // S, L or M will get at least one "processOp", if M, it gets a 2nd
        stat1 = processOp(cache, address, cacheParams);
        if(operation == 'M')
        {
          stat2 = processOp(cache, address, cacheParams);
          if(stat2 == 0) 
          {
            stats.misses++;
            string2 = " miss";
          }
          else if(stat2 == 1)
          {
            stats.hits++;
            string2 = " hit";
          }
          else
          {
            stats.misses++;
            stats.evictions++;
            string2 = " miss eviction";
          }
        }
      
      
        if(stat1 == 0) 
        {
          stats.misses++;
          string1 = " miss";
        }
        else if(stat1 == 1)
        {
          stats.hits++;
          string1 = " hit";
        }
        else
        {
          stats.misses++;
          stats.evictions++;
          string1 = " miss eviction";
        }

        // If verbosity is on, print out some details of the operations
        if(isVerbose == 1)
        {
          printf("%c %x,%d", operation, address, size);
          printf("%s %s\n", string1, string2);
        }
        
      }
      
    }
    fclose(file);
  }
  else
  {
    perror(traceFile);
  }

  return stats;
}


// Since this isn't a real cache, I don't care what the op is...
// L and S get 1 call, M gets 2 calls.
// 0 return is miss, 1 return is hit, 2 return is miss eviction
int processOp(struct Cache cache, unsigned long long int address, struct CacheParams cacheParams)
{
  // Get the size of the tag
  int tagSize = 64 - (cacheParams.s + cacheParams.b);

  // Get tag that will be compared against other lines
  unsigned long actualTag = address >> (cacheParams.s + cacheParams.b);

  // Get the set we will be storing our data in
  unsigned long shiftedAdd = address << tagSize;
  unsigned long setIndex = shiftedAdd >> (tagSize + cacheParams.b);
  struct CacheSet set = cache.sets[setIndex];

  // Make each line in that set "1 step older"
  for(int i = 0; i < cacheParams.e; ++i)
  {
    set.lines[i].time++;
  }

  // Check for a hit
  for(int i = 0; i < cacheParams.e; ++i)
  {
    if(set.lines[i].tag == actualTag && set.lines[i].valid == 1)
    {
      set.lines[i].time = 0;
      return 1;
    }
  }

  // At this point we didn't hit, we either have a blank spot or we have to evict a line
  int emptyIndex = checkForEmpty(set, cacheParams.e);

  // We found an empty line to insert our element into
  if(emptyIndex != -1)
  {
    set.lines[emptyIndex].tag = actualTag;
    set.lines[emptyIndex].valid = 1;
    set.lines[emptyIndex].time = 0;
    return 0;
  }

  // Alright, we didn't hit or find an empty line. We have to do an eviction.
  int evictionIndex = evictLine(set, cacheParams.e);
  
  set.lines[evictionIndex].tag = actualTag;
  set.lines[evictionIndex].valid = 1;
  set.lines[evictionIndex].time = 0;

  return 2;

}

// Returns index of empty line or -1 (no empty line)
int checkForEmpty(struct CacheSet set, int e)
{
  for (int i = 0; i < e; ++i)
  {
    if (set.lines[i].valid == 0)
    {
      return i;
    }
  }
  return -1;
}

// Returns oldest line and this will be evicted
int evictLine(struct CacheSet set, int e)
{
  int oldestCount = 0;
  int oldestIndex = -1;
  
  // Loop over all the columns in the line. Return the oldest index.
  for (int i = 0; i < e; ++i)
  {
    struct CacheLine line = set.lines[i];
    if(line.time > oldestCount)
    {
      oldestCount = line.time;
      oldestIndex = i;
    }

  }
  return oldestIndex;
}



struct Cache makeCache(struct CacheParams cacheParams)
{
  // Gives us 2^s
  int bigS = 1 << cacheParams.s;

  struct Cache cache;
  struct CacheSet cacheSet;
  struct CacheLine line;

  // Allocating enough memory for all the sets we will need in the cache
  cache.sets = (struct CacheSet*) malloc(sizeof(struct CacheSet) * bigS);

  for(int i = 0; i < bigS; ++i)
  {

    cacheSet.lines = (struct CacheLine*) malloc(sizeof(struct CacheLine) * cacheParams.e);
    cache.sets[i] = cacheSet;

    for (int j = 0; j < cacheParams.e; ++j)
    {
      line.valid = 0;
      line.tag = 0;
      line.time = 0;
      cacheSet.lines[j] = line;

    }

  }
  return cache;
}


// Freeing the memory we malloc'ed
void cleanUp(struct Cache cache, int s, int e)
{
  int bigS = 1 << s;

  // Freeing all the lines
  for(int i = 0; i < bigS; ++i)
  {
    struct CacheSet set = cache.sets[i];
    if(set.lines != NULL)
    {
      free(set.lines);
    }
  }

  // Freeing all the sets
  if(cache.sets != NULL)
  {
    free(cache.sets);
  }

}


int main(int argc, char **argv)
{
  // Getting the command line options defining the cache and trace file that
  // we will be working with
  struct CacheParams cacheParams = captureOptions(argc,argv);

  // Make a new cache to work with. Sets and Lines will get created here too
  struct Cache cache = makeCache(cacheParams);


  struct CacheStats stats;
  stats.hits = 0;
  stats.misses = 0;
  stats.evictions = 0;

  // Process the trace file we were given and generate the hit/miss/evictions statistics
  stats = processTraceFile(cacheParams.traceFile, cache, cacheParams, stats, cacheParams.verbose); 

  printSummary(stats.hits, stats.misses, stats.evictions);

  cleanUp(cache, cacheParams.s, cacheParams.e);
  return 0;
}

