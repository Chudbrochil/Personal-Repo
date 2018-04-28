/* 
 * trans.c - Matrix transpose B = A^T
 *
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */ 
#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);
void trans(int M, int N, int A[N][M], int B[M][N]);
void trans32(int M, int N, int A[N][M], int B[M][N]);
void trans64(int M, int N, int A[N][M], int B[M][N]);
void trans67(int M, int N, int A[N][M], int B[M][N]);


/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 */
char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N])
{
  if(N == 32)
  {
    trans32(M, N, A, B);
  }
  else if(N == 64)
  {
    trans64(M, N, A, B);
  }
  else
  {
    trans67(M, N, A, B);
  }
}

/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";
void trans(int M, int N, int A[N][M], int B[M][N])
{
    int i, j, tmp;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; j++) {
            tmp = A[i][j];
            B[j][i] = tmp;
        }
    }    
}


char trans32_desc[] = "Transpose for 32x32 matrix.";
void trans32(int M, int N, int A[N][M], int B[M][N])
{
  int blockSize = 8;
  int i, j, k, l;

  int diagElement;
  int diagIndex;

  // Outer loop for gathering each block. 8x8 for the 32x32 matrix
  for(i = 0; i < M; i += blockSize)
  {
    for(j = 0; j < N; j += blockSize)
    {

      // Inner loop for the transpose
      for(k = j; k < j + blockSize; ++k)
      {
        for(l = i; l < i + blockSize; ++l)
        {
          if(k != l)
          {
            B[l][k] = A[k][l];
          }
          // Assign the diagonal element and index for later use
          else
          {
            diagElement = A[k][l];
            diagIndex = l;
          }
          
        }

        // We can save some misses on the diagonals
        if(i == j)
        {
          B[diagIndex][diagIndex] = diagElement;
        }
      }
    }
  }
}


char trans64_desc[] = "Transpose for 64x64 matrix.";
void trans64(int M, int N, int A[N][M], int B[M][N])
{

  int blockSize = 4;
  int i, j, k, l;

  int diagElement;
  int diagIndex;

  // Outer loop for gathering each block. 4x4 for 64 matrix
  for(i = 0; i < M; i += blockSize)
  {
    for(j = 0; j < N; j += blockSize)
    {

      // Inner loop for the transpose
      for(k = j; k < j + blockSize; ++k)
      {
        for(l = i; l < i + blockSize; ++l)
        {
          if(k != l)
          {
            B[l][k] = A[k][l];
          }
          else
          {
            diagElement = A[k][l];
            diagIndex = l;
          }
          
        }

        // We can save some misses on the diagonals
        if(i == j)
        {
          B[diagIndex][diagIndex] = diagElement;
        }
      }
    }
  }
}



char trans67_desc[] = "Transpose for 61x67 matrix.";
void trans67(int M, int N, int A[N][M], int B[M][N])
{

  int blockSize = 4;
  int i, j, k, l;

  int diagElement;
  int diagIndex;

  int optimizedBound = 60;

  // Outer loop for gathering each block. 4x4 upto 60x60 for the 61x67 matrix
  for(i = 0; i < optimizedBound; i += blockSize)
  {
    for(j = 0; j < optimizedBound; j += blockSize)
    {

      // Inner loop for the transpose
      for(k = j; k < j + blockSize; ++k)
      {
        for(l = i; l < i + blockSize; ++l)
        {
          //B[l][k] = A[k][l];
          
          if(k != l)
          {
            B[l][k] = A[k][l];
          }
          else
          {
            diagElement = A[k][l];
            diagIndex = l;
          }
          
        }

        // We can save some misses on the diagonals
        if(i == j)
        {
          B[diagIndex][diagIndex] = diagElement;
        }
      }
    }
  }

  // This part is totally un-optimized, I'm just finishing the transpose
  // Getting the right most ones we left out
  for(i = optimizedBound; i < N; ++i)
  {
    for(j = 0; j < M; ++j)
    {
      A[i][j] = B[j][i];
    }
  }
  // Getting the bottom most ones we left out
  for(i = 0; i < optimizedBound; ++i)
  {
    for(j = optimizedBound; j < M; ++j)
    {
      A[i][j] = B[j][i];
    }
  }

}




/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions()
{
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    registerTransFunction(trans, trans_desc); 

    registerTransFunction(trans32, trans32_desc);
    registerTransFunction(trans64, trans64_desc);
    registerTransFunction(trans67, trans67_desc);
}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N])
{
    int i, j;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}

