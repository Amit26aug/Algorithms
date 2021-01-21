package com.august.algorithms.coursera.sorting.quicksort;

import java.util.Random;

// "static void main" must be defined in a public class.
public class QuickSelectImpl {
    public static void main(String[] args) {
        Integer[] a = {90, 40, 80, 50, 60, 20, 10, 80};
        // for(int i: a) System.out.print(i + "-");
        System.out.println(QuickSelect.findKthMin(a, 8));
    }
}

class QuickSelect {
    
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
        while(true) {
            while(less(a[++i], a[left])) if (i == right) break;
            while(less(a[left], a[--j]));
            
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, left, j);
        return j;
    }
    
    public static Comparable findKthMin(Comparable[] a, int k) {
        assert (k >0 && k <= a.length);
        shuffle(a);
        k--;
        int left = 0, right = a.length - 1;
        while(left < right) {
            int j = partition(a, left, right);
            if (j < k) left = j + 1;
            else if (j > k) right = j - 1;
            else return a[k];
        }
        return a[k];
    }
    
   
}
