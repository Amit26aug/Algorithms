package com.august.gfg450.array7;

import java.util.Arrays;

public class RotateArray {

  public static void main(String[] args) {
    long[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
    rotateLeftByN(arr, 5);
    System.out.println(Arrays.toString(arr));
    rotateRightBy1(arr);
    System.out.println(Arrays.toString(arr));
  }

  public static void rotateLeftByN(long[] arr, int n) {
    int size = arr.length;
    reverse(arr, 0, size - n - 1);
    reverse(arr, size - n, size - 1);
    reverse(arr, 0, size - 1);
  }

  public static void rotateRightBy1(long[] arr) {
    int size = arr.length;
    reverse(arr, 0, size - 2);
    reverse(arr, 0, size - 1);
  }

  static void swap(long[] arr, int i, int j) {
    long temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  static void reverse(long[] arr, int start, int end) {
    while (start < end) {
      swap(arr, start, end);
      start++;
      end--;
    }
  }

}
