package com.august.gfg450.array8_13_kadane;

public class KadaneAlgo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -2, 5};
        System.out.println(findSubArrayMaxSum(arr));
    }

    static int findSubArrayMaxSum(int[] arr) {
        int maxSum = arr[0];
        int curSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curSum = Math.max(curSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
