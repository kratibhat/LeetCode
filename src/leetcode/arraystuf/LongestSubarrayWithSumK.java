package leetcode.arraystuf;
import java.util.HashMap;
import java.util.Map;
//Given an array arr[] of size n containing integers, the task is to find the length of the longest subarray having sum equal to the given value k.
//
//Note: If there is no subarray with sum equal to k, return 0.
//
//Examples:
//
//Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
//Output: 6
//Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.
//
//Input: arr[] = [-5, 8, -14, 2, 4, 12], k = -5
//Output: 5
//Explanation: Only subarray with sum = 15 is [-5, 8, -14, 2, 4] of length 5.
//
//Input: arr[] = [10, -10, 20, 30], k = 5
//Output: 0
//Explanation: No subarray with sum = 5 is present in arr[].
public class LongestSubarrayWithSumK {
    // JavaScript program to find longest sub-array having sum k

    static int longestSubarray(int[] arr, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        int prefSum = 0;

        for (int i = 0; i < arr.length; ++i) {
            prefSum += arr[i];

            // Check if the entire prefix sums to k
            if (prefSum == k)
                res = i + 1;

                // If prefixSum - k exists in the map then there exist such
                // subarray from (index of previous prefix + 1) to i.
            else if (mp.containsKey(prefSum - k))
                res = Math.max(res, i - mp.get(prefSum - k));

            // Store only first occurrence index of prefSum
            if (!mp.containsKey(prefSum))
                mp.put(prefSum, i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, -10};
        int k = 15;
        System.out.println(longestSubarray(arr, k));
    }
}