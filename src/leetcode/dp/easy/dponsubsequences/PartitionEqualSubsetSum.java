package leetcode.dp.easy.dponsubsequences;
//Given an integer array nums, return true if you can partition the array into two
// subsets such that the sum of the elements in both subsets is equal or false otherwise.
//
//
//
//Example 1:
//
//Input: nums = [1,5,11,5]
//Output: true
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
//Example 2:
//
//Input: nums = [1,2,3,5]
//Output: false
//Explanation: The array cannot be partitioned into equal sum subsets.
//
//
//Constraints:
//
//1 <= nums.length <= 200
//1 <= nums[i] <= 100
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        /// Can I make a subset whose sum is i using the numbers processed so far?
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                if (dp[i - num]) {
                    dp[i] = true;
                }
            }
            if (dp[target]) {
                return true;
            }
        }

        return dp[target];
    }
    public static void main(String []args){
        PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();
        int[] nums = {1, 5, 11, 5};
        System.out.println(solution.canPartition(nums)); // Output: true
    }
}
////When each element can be used only once (0/1 Knapsack, Partition Equal Subset Sum, Subset Sum), iterate the DP array from right to left.
///
/// When each element can be used unlimited times (Unbounded Knapsack, Coin Change, Combination Sum IV), iterate from left to right.