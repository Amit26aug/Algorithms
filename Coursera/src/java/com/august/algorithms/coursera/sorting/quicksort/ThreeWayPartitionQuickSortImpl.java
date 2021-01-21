package com.august.algorithms.coursera.sorting.quicksort;

import java.util.Random;

public class ThreeWayPartitionQuickSortImpl {
    public static void main(String[] args) {
        Integer[] a = {90, 40, 80, 50, 60, 20, 10, 80};
        ThreeWayPartitionQuickSort.threeWaySort(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class ThreeWayPartitionQuickSort {
    
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static void shuffle(Comparable[] a) {
        Random random = new Random();
        for(int i = 1; i < a.length; i++) {
            exchange(a, i, random.nextInt(i + 1));
        }
    }

    
    private static void threeWaySort(Comparable[] a, int left, int right) {        
        if (left >= right) return;
        
        int lt = left, i = left + 1, gt = right;
        Comparable v = a[left];
        while(i <= gt) {
            int compare = a[i].compareTo(v);
            if (compare < 0) exchange(a, i++, lt++);
            else if (compare > 0) exchange(a, i, gt--);
            else i++;
        }
        threeWaySort(a, left, lt -1);
        threeWaySort(a, gt + 1, right);
    }
    
    public static void threeWaySort(Comparable[] a) {
        shuffle(a);
        threeWaySort(a, 0, a.length - 1);
    }
    
}
