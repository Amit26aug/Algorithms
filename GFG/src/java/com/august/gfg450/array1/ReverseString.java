package com.august.gfg450.array1;

public class ReverseString {

  public static void main(String[] args) {
    String test = "reverseThis";
    System.out.println(reverseString(test));
  }

  private static String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
  }
}
