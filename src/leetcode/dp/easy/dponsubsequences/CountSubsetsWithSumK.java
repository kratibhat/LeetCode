package leetcode.dp.easy.dponsubsequences;

import java.util.*;
/// /Problem Statement : Given an array arr of n integers and an integer K, count the number of subsets of the given array that have a sum equal to K.
///
/// Examples
/// Input: arr = [1, 2, 2, 3], K = 3
/// Output: 3
///
/// Explanation: The subarrays [1,2], [1,2] and [3] have a sum of 3.
/// Input: arr = [1, 2, 3, 4, 5], K = 5
/// Output: 3
/// Explanation: The subsets are [5], [2, 3], and [1, 4].
public class CountSubsetsWithSumK {
    
    public int countSubsets(int[] arr, int K) {
        // Create dp array of size K+1
        int[] dp = new int[K + 1];

        // Base case: Empty set makes sum 0
        dp[0] = 1;

        // If first element <= K, mark it
        if (arr[0] <= K) dp[arr[0]] += 1;

        // Loop over elements
        for (int i = 1; i < arr.length; i++) {
            // Temporary array for current iteration
            int[] curr = new int[K + 1];

            // Base case: Empty set makes sum 0
            curr[0] = 1;

            // Check all target sums
            for (int t = 0; t <= K; t++) {
                // Exclude current element
                int notTake = dp[t];

                // Include current element if possible
                int take = 0;
                if (arr[i] <= t) {
                    take = dp[t - arr[i]];
                }

                // Sum of both choices
                curr[t] = take + notTake;
            }

            // Update dp
            dp = curr;
        }

        // Return answer
        return dp[K];
    }

    public static void main(String[] args) {
        CountSubsetsWithSumK sol = new CountSubsetsWithSumK();
        int[] arr = {1, 2, 3};
        int K = 4;
        
        System.out.println("Total subsets with sum " + K + " is: " + sol.countSubsets(arr, K)); 
    }
}