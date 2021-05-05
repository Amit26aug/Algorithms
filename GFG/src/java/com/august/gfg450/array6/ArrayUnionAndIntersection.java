package com.august.gfg450.array6;

import java.util.HashSet;
import java.util.Set;

public class ArrayUnionAndIntersection {

  public static void main(String[] args) {
    int[] arr1 = {1, 2, 3, 4, 5, 6};
    int[] arr2 = {3, 4, 5, 6, 7, 8};

    System.out.println("Intersection Count: " + intersectionCount(arr1, arr2));
    System.out.println("Union Count: " + unionCount(arr1, arr2));
  }

  static int intersectionCount(int[] a, int[] b) {
    Set<Integer> setA = new HashSet<>();
    Set<Integer> setB = new HashSet<>();
    for (int i: a) setA.add(i);
    for (int i: b) setB.add(i);

    int count = 0;
    for (int i: setA) {
      if (setB.contains(i)) count++;
    }

    return count;
  }

  static int unionCount(int[] a, int[] b) {
    Set<Integer> set = new HashSet<>();
    for (int i: a) set.add(i);
    for (int i: b) set.add(i);

    return set.size();
  }

}
