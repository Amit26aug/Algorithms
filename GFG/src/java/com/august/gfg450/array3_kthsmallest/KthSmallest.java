package com.august.gfg450.array3_kthsmallest;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem statement
 * Given an array arr[] and a number K where K is smaller than size of array,
 * the task is to find the Kth smallest element in the given array.
 * It is given that all array elements are distinct.
 *
 * Constraints:
 * 1 <= N <= 10^5
 * 1 <= arr[i] <= 10^5
 * 1 <= K <= N
 */
public class KthSmallest {

  public static void main(String[] args) {
    int[] arr = {4, 7, 1, 3, 2, 6, 5};
    System.out.println(getKthSmallest(arr, 5));
  }

  static int getKthSmallest(int[] arr, int k) {
    Set<Integer> set = new HashSet<>();
    for (int j : arr) {
      set.add(j);
    }
    int count = 0;
    int start = 0;
    while (count != k) {
      start++;
      if (set.contains(start)) {
        count++;
      }
    }
    return start;
  }

}


