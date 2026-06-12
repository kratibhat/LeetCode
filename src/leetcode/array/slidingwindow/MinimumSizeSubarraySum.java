package leetcode.array.slidingwindow;
//Given an array of positive integers nums and a positive
// integer target, return the minimal length of a subarray whose sum is
// greater than or equal to target. If there is no such subarray, return 0 instead.
//
//
//
//Example 1:
//
//Input: target = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: The subarray [4,3] has the minimal length under the problem constraint.
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLen = Integer.MAX_VALUE; // Start with a massive value to find the minimum

        for (int right = 0; right < nums.length; right++) {
            // 1. Expand the window
            currentSum += nums[right];

            // 2. Shrink the window as long as it satisfies the condition
            while (currentSum >= target) {
                // Record the smallest window length found so far
                minLen = Math.min(minLen, right - left + 1);

                // Discard the leftmost element and slide 'left' forward
                currentSum -= nums[left];
                left++;
            }
        }

        // If minLen was never updated, it means no valid subarray exists
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
    public static void main(String[] args) {
        MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        int result = solution.minSubArrayLen(target, nums);
        System.out.println("Minimum Length of Subarray: " + result); // Output: 2
    }
}
