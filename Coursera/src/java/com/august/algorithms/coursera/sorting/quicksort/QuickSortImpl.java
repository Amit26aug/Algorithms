package com.august.algorithms.coursera.sorting.quicksort;

import java.util.Random;

// "static void main" must be defined in a public class.
public class QuickSortImpl {
    public static void main(String[] args) {
        Integer[] a = {90, 40, 80, 50, 60, 20, 10, 80};
        QuickSort.sort(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class QuickSort {
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
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
    
    private static int partition(Comparable[] a, int left, int right) {
        int i = left, j = right + 1;
        System.out.println(left + ":" + right);
        while(true) {
            while(less(a[++i], a[left])) if (i == right) break;
            while(less(a[left], a[--j])) if (j == left) break;
        
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, left, j);
        return j;
    }
    
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, int left, int right) {
        if (left >= right) return;
        int j = partition(a, left, right);
        sort(a, left, j - 1);
        sort(a, j + 1, right);
    }
    
}
