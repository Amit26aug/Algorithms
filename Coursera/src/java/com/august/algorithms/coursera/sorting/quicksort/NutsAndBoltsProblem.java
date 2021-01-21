package com.august.algorithms.coursera.sorting.quicksort;

import java.util.Random;

public class NutsAndBoltsProblem {
    public static void main(String[] args) {
        Integer[] nuts = {2, 5, 3, 1, 6};
        Integer[] bolts = {6, 1, 3, 2, 5};
        
        NutsAndBolts.matchNutsAndBolts(nuts, bolts);
        
        for(int nut: nuts) System.out.print(nut + "-");
        System.out.println();
        for(int bolt: bolts) System.out.print(bolt + "-");
    }
}

class NutsAndBolts{
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }
    
    private static void shuffle(Comparable[] a) {
        Random random = new Random();
        for(int i = 1; i < a.length; i++) exchange(a, i, random.nextInt(i + 1));
    }
    
    private static int partition(Comparable[] a, Comparable pivot, int left, int right) {
        int j = left;
        for(int i = left + 1; i <= right; i++) {
            if(less(a[i], pivot)) exchange(a, ++j, i);
            else if(equal(a[i], pivot)){
                exchange(a, left, i);
                i--;
            }
        }
        exchange(a, j, left);
        // System.out.println(pivot + ":" + left + ":" + right);
        return j;
    }
    
    private static void sort(Comparable[] a, Comparable[] b, int left, int right) {
        if (left >= right) return;
        int j = partition(a, a[left], left, right);
        int j2 = partition(b, a[j], left, right);
        // System.out.println(j == j2);
        sort(a, b, left, j - 1);
        sort(a, b, j + 1, right);
    }
    
    public static void matchNutsAndBolts(Comparable[] nuts, Comparable[] bolts) {
        assert nuts.length == bolts.length;
        shuffle(nuts);
        shuffle(bolts);
        // for(Comparable c: nuts) System.out.print(c + "=");
        // for(Comparable c: bolts) System.out.print(c + "=");
        // System.out.println();
        sort(nuts, bolts, 0, nuts.length - 1);
    }
}
