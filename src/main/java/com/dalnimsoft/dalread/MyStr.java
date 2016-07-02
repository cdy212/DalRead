package com.dalnimsoft.dalread;

import java.util.Arrays;
import java.util.List;

public class MyStr {

	
	public List<String> sortArraryByLen(List<String> list) {	
		String[] arr = new String[list.size()];
		arr = list.toArray(arr);
		sortByLen(arr);
		List<String> listSortedByLength = Arrays.asList(arr);
		return listSortedByLength;
	}
	
    public void sortByLen(String[] arr) {
        boolean swapped = false;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i += 1) {
                if (arr[i].length() < arr[i + 1].length()) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    // Mutates the original array
    public void swap(String[] arr, int index0, int index1) {
        String temp = arr[index0];
        arr[index0] = arr[index1];
        arr[index1] = temp;
    }
}
