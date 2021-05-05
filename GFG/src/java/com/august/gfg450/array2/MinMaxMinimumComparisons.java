package com.august.gfg450.array2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MinMaxMinimumComparisons {

  static class Pair {
    int min, max;
  }

  public static void main(String[] args) {
    int[] arr = {4, 7, 1, 3, 2, 6, 5};
    Pair result = getMinMax(arr);
    System.out.println("Minimum: " + result.min);
    System.out.println("Maximum: " + result.max);
  }

  private static Pair getMinMax(int[] arr) {
    if (arr.length == 0) {
      throw new IllegalArgumentException();
    }

    int comparisons = 0;

    Pair result = new Pair();
    if (arr.length == 1) {
      result.min = result.max = arr[0];
      comparisons++;
      System.out.println("Comparisons: " + comparisons);
      return result;
    }

    if (arr.length % 2 == 0) {
      if (arr[0] > arr[1]) {
        result.min = arr[1];
        result.max = arr[0];
      } else {
        result.min = arr[0];
        result.max = arr[1];
      }
      comparisons++;
    } else {
      result.min = result.max = arr[0];
    }

    int startIndex = arr.length % 2 == 0 ? 2 : 1;
    for (int i = startIndex; i < arr.length; i += 2) {
      if (arr[i] < arr[i + 1]) {
        if (result.min > arr[i]) {
          result.min = arr[i];
        }
        if (result.max < arr[i + 1]) {
          result.max = arr[i + 1];
        }
      } else {
        if (result.min > arr[i + 1]) {
          result.min = arr[i + 1];
        }
        if (result.max < arr[i]) {
          result.max = arr[i];
        }
      }
      comparisons += 2;
    }

    System.out.println("Comparisons: " + comparisons);
    return result;
  }
}
