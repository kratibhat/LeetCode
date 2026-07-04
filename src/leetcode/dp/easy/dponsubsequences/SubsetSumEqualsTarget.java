package leetcode.dp.easy.dponsubsequences;
/// Problem Statement: We are given an array ‘ARR’ with N positive integers. We need to find if there is a subset in “ARR” with a sum equal to K. If there is, return true else return false.
///
/// A subset/subsequence is a contiguous or non-contiguous part of an array, where elements appear in the same order as the original array.
/// For example, for the array: [2,3,1] , the subsequences will be [{2},{3},{1},{2,3},{2,1},{3,1},{2,3,1}} but {3,2} is not a subsequence because its elements are not in the same order as the original array.
///
/// Examples
///
/// Input :  N = 4, ARR = [4, 3, 5, 2], K = 6
/// Output : true
/// Explanation : One possible subset with sum = 6 is [4, 2]. There’s also [3, 3] but that doesn’t exist in the array. As soon as we find one subset whose sum is equal to K, the answer is true.
///
/// Input : N = 3, ARR = [1, 2, 5], K = 4
/// Output : false
/// Explanation : Possible subsets and their sums: [1] → 1, [2] → 2, [5] → 5, [1,2] → 3, [1,5] → 6, [2,5] → 7, [1,2,5] → 8. None of them equal 4, so the answer is false.
public class SubsetSumEqualsTarget {
    // Function to check if subset with sum k exists using space optimized DP
    public boolean subsetSumToK(int n, int k, int[] arr) {
        // Initialize previous row of DP table with false
        boolean[] prev = new boolean[k + 1];

        // Base case: sum 0 can always be formed with empty subset
        prev[0] = true;

        // Base case: if first element <= k, mark true
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        // Iterate over elements starting from second
        for (int ind = 1; ind < n; ind++) {
            // Current row of DP table
            boolean[] cur = new boolean[k + 1];
            cur[0] = true; // sum 0 always possible

            for (int target = 1; target <= k; target++) {
                // Option 1: not take current element
                boolean notTaken = prev[target];

                // Option 2: take current element if possible
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = prev[target - arr[ind]];
                }

                // Store true if either option is true
                cur[target] = notTaken || taken;
            }
            // Move current row to previous for next iteration
            prev = cur;
        }

        // Return if sum k is possible using all elements
        return prev[k];
    }



    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 4;
        SubsetSumEqualsTarget sol = new SubsetSumEqualsTarget();

        if (sol.subsetSumToK(arr.length, k, arr)) {
            System.out.println("Subset with the given target found");
        } else {
            System.out.println("Subset with the given target not found");
        }
    }
}
