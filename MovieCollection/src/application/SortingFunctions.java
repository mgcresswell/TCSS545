package application;

import java.util.concurrent.TimeUnit;

public class SortingFunctions 
{
	public static int[] BubbleSort(int array[], int i)
	{		
		for (int j = 0; j < array.length - (i) - 1; j++) 
        {
            if (array[j] > array[j + 1]) 
            {
                final int tmp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = tmp;
            }               
        }
		return array;
	}
	
	public static int[] InsertionSort(int array[], int i)
	{		
		int key = array[i]; 
        int j = i - 1; 
        while (j >= 0 && array[j] > key) { 
        	array[j + 1] = array[j]; 
            j = j - 1; 
        } 
        array[j + 1] = key; 
		return array;
	}
	
	public static int[] SelectionSort(int array[], int i)
	{		
        int min_idx = i; 
        for (int j = i+1; j < array.length; j++) 
            if (array[j] < array[min_idx]) 
                min_idx = j; 

        int temp = array[min_idx]; 
        array[min_idx] = array[i]; 
        array[i] = temp; 
		return array;
	}
	
	public static void mergeSort(int arr[], int n)
	{
		int curr_size;  
        int left_start; 
        
        for (curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size) 
        { 	                  
            for (left_start = 0; left_start < n-1; left_start += 2*curr_size) 
            { 
                int mid = Math.min(left_start + curr_size - 1, n-1);             
                int right_end = Math.min(left_start + 2*curr_size - 1, n-1); 
                merge(arr, left_start, mid, right_end); 
            } 
        } 
	}

	 public static void merge(int arr[], int l, int m, int r) 
	 { 
	        int i, j, k; 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	      
	        /* create temp arrays */
	        int L[] = new int[n1]; 
	        int R[] = new int[n2]; 
	      
	        /* Copy data to temp arrays L[] 
	        and R[] */
	        for (i = 0; i < n1; i++) 
	            L[i] = arr[l + i]; 
	        for (j = 0; j < n2; j++) 
	            R[j] = arr[m + 1+ j]; 
	      
	        /* Merge the temp arrays back into 
	        arr[l..r]*/
	        i = 0; 
	        j = 0; 
	        k = l; 
	        while (i < n1 && j < n2) 
	        { 
	            if (L[i] <= R[j]) 
	            { 
	                arr[k] = L[i]; 
	                i++; 
	            } 
	            else
	            { 
	                arr[k] = R[j]; 
	                j++; 
	            } 
	            k++; 
	        } 
	      
	        /* Copy the remaining elements of  
	        L[], if there are any */
	        while (i < n1) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	            k++; 
	        } 
	      
	        /* Copy the remaining elements of 
	        R[], if there are any */
	        while (j < n2) 
	        { 
	            arr[k] = R[j]; 
	            j++; 
	            k++; 
	        } 
	    } 
	

	 public static int partition(int arr[], int low, int high) 
	 { 
	        int pivot = arr[high]; 
	        int i = (low - 1); 
	        for (int j = low; j <= high - 1; j++) 
	        { 
	            if (arr[j] <= pivot) 
	            { 
	                i++; 	               
	                int temp = arr[i]; 
	                arr[i] = arr[j]; 
	                arr[j] = temp; 
	            } 
	        } 
	  
	        int temp = arr[i + 1]; 
	        arr[i + 1] = arr[high]; 
	        arr[high] = temp; 	  
	        return i + 1; 
	 }
	  
	 public static void quickSortIterative(int arr[], int l, int h) 
	 { 
	        int[] stack = new int[h - l + 1]; 
	        int top = -1; 
	        stack[++top] = l; 
	        stack[++top] = h;  
	        while (top >= 0) 
	        { 
	            h = stack[top--]; 
	            l = stack[top--]; 
	            int p = partition(arr, l, h); 
	            if (p - 1 > l) 
	            { 
	                stack[++top] = l; 
	                stack[++top] = p - 1; 
	            } 
	            
	            if (p + 1 < h) 
	            { 
	                stack[++top] = p + 1; 
	                stack[++top] = h; 
	            } 
	        } 
	  } 
}
