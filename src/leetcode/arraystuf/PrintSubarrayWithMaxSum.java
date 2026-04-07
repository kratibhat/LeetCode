package leetcode.arraystuf;

import java.util.ArrayList;
import java.util.List;

public class PrintSubarrayWithMaxSum {
    static List<Integer> maxSumSubarray(int[] arr) {
        // start and end of max sum subarray
        int resStart = 0, resEnd = 0;

        // Initialize the maximum subarray sum with the first element
        int maxSum = arr[0];

        for (int i = 0; i < arr.length; i++) {

            // Initialize current subarray sum with 0
            int currSum = 0;
            for (int j = i; j < arr.length; j++) {
                currSum += arr[j];

                // If current subarray has greater sum than maximum sum subarray,
                // then update the start and end of maximum sum subarray
                if (currSum > maxSum) {
                    maxSum = currSum;
                    resStart = i;
                    resEnd = j;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = resStart; i <= resEnd; i++)
            res.add(arr[i]);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        List<Integer> res = maxSumSubarray(arr);

        for (int num : res)
            System.out.print(num + " ");
        System.out.println();
    }
}
