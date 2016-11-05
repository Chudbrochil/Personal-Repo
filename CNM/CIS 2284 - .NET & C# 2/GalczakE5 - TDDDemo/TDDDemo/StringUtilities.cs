using System;

namespace TDDDemo
{
    public class StringUtilities
    {
        public StringUtilities()
        {
        }

        public object CountOccurences(string stringToCheck, string stringToFind)
        {
            var occurrenceCount = 0;
            if (stringToCheck == null)
            {
                occurrenceCount = -1;
            }
            else
            {
                var stringCharArray = stringToCheck.ToLower().ToCharArray();
                var stringToCheckForAsChar = stringToFind.ToLower().ToCharArray()[0];
                for (var cIndex = 0; cIndex < stringCharArray.Length; cIndex++)
                {
                    if (stringCharArray[cIndex] == stringToCheckForAsChar) occurrenceCount++;
                }
            }
            return occurrenceCount;
        }


    }
}