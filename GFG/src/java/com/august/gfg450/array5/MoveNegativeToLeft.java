package com.august.gfg450.array5;

public class MoveNegativeToLeft {
  public static void main(String[] args) {
    int[] arr = {-1, 3, 5, -4, 2, -9, 0, 4, -5};
    moveNegativesToLeft(arr);
    for (int a: arr) {
      System.out.println(a);
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void moveNegativesToLeft(int[] a) {
    int i = 0, j = a.length - 1;
    while (i < j) {
      while (i < j && a[i] < 0) i++;
      while (i < j && a[j] > 0) j--;
      swap(a, i, j);
    }

  }
}
