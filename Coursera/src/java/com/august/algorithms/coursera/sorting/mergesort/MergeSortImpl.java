package com.august.algorithms.coursera.sorting.mergesort;

// "static void main" must be defined in a public class.
public class MergeSortImpl {
    public static void main(String[] args) {
        
        Integer[] a = {90, 30, 50, 40, 20, 60, 10, 80};
        BottomUpMergeSort.sort(a);
        for(int i: a) System.out.print(i + "-");
        
    }
}

class MergeSort {
    
    private static boolean isSorted(Comparable[] a, int start, int end) {
        for(int i = start; i < end; i++) if (a[i + 1].compareTo(a[i]) < 0) return false;
        return true;
    }
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void merge(Comparable[] a, Comparable[] aux, int left, int mid, int right) {
        assert isSorted(a, left, mid);
        assert isSorted(a, mid + 1, right);
        
        for(int i = left; i <= right; i++) aux[i] = a[i];
        
        int i = left, j = mid + 1;
        for(int k = left; k <= right; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > right) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
        
        assert isSorted(a, left, right);
    }
    
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        sort(a, aux, left, mid);
        sort(a, aux, mid + 1, right);
        merge(a, aux, left, mid, right);
    }
}
