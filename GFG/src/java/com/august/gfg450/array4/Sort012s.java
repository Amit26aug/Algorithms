package com.august.gfg450.array4;

public class Sort012s {
  public static void main(String[] args) {
    int[] arr = {1, 0, 2, 1, 0, 0, 1, 2, 1};
    sort012(arr, arr.length);
    for (int a: arr) {
      System.out.println(a);
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void sort012(int[] a, int n) {
    int track0, track1, track2;
    track0 = 0;
    track1 = 0;
    track2 = n - 1;

    while (track1 <= track2) {
      switch (a[track1]) {
        case 0 -> {
          swap(a, track1, track0);
          track0++;
          track1++;
        }
        case 1 -> track1++;
        case 2 -> {
          swap(a, track1, track2);
          track2--;
        }
      }
    }

  }
}
