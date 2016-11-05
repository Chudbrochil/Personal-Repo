//Barbara Johnston and Natalie Swanteson-Franz 
//File: P3_TemplateSort.h
//A file with a template Shell sort

//How to use this class
//First, #include "P3_TemplateSort.h" in the file where the sort is needed.
//Next, declare a TemplateSort object and call the shellSort method.

//TemplateSort< datatype >  tSort;
//where datatype is the type of the array you are sorting
//such as string, int, double, etc. 
//TemplateSort<string>  tSort;
//call the method like this
//tSort.shellSort( arrayName, total);
//where arrayName is a string array


#ifndef _TEMPLATESORT_H
#define _TEMPLATESORT_H

template <class X> class TemplateSort
{
public:
	TemplateSort();
	void shellSort(X arr[], int size);
};

template <class X>
TemplateSort<X>::TemplateSort()
{

}
template <class X>
void TemplateSort<X>::shellSort(X arr[], int size)
{
	int h = 1;
        /* 
         * find the largest h value possible 
         */
    while ((h * 3 + 1) < size) 
	{
		h = 3 * h + 1;
	}

    /* 
     * while h remains larger than 0 
     */
    while( h > 0 ) 
	{
        /* 
         * for each set of elements (there are h sets)
         */
        for (int i = h - 1; i < size; i++) 
		{
            /* 
             * pick the last element in the set
             */
            X B = arr[i];
            int j = i;
            /*
             * compare the element at B to the one before it in the set
             * if they are out of order continue this loop, moving
             * elements "back" to make room for B to be inserted.
             */
            for( j = i; (j >= h) && (arr[j-h] > B); j -= h) 
			{
                arr[j] = arr[j-h];
            }
            /*
             *  insert B into the correct place
             */
            arr[j] = B;
        }
        /* 
         * all sets h-sorted, now decrease set size
         */
        h = h / 3;
    }



}

#endif
