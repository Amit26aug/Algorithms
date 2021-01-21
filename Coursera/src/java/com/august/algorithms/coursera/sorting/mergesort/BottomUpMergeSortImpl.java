package com.august.algorithms.coursera.sorting.mergesort;

// "static void main" must be defined in a public class.
public class BottomUpMergeSortImpl {
    public static void main(String[] args) {
        
        Integer[] a = {90, 30, 50, 40, 20, 60, 10, 80};
        BottomUpMergeSort.sort(a);
        for(int i: a) System.out.print(i + "-");
        
    }
}

class BottomUpMergeSort {
    
    
    private static boolean isSorted(Comparable[] a, int start, int end) {
        for(int i = start; i < end; i++) if (less(a[i + 1], a[i])) return false;
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
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        // for(int i = 0; i < a.length; i++) aux[i] = a[i];
        // sort(a, aux, 0, a.length - 1);
        for(int sz = 1; sz < n; sz = 2 * sz) {
            for(int i = 0; i < n - sz; i += 2 * sz)
                merge(a, aux, i, i + sz - 1, Math.min(i + 2 * sz - 1, n - 1));
        }
    }
    
}
