package leetcode.array.slidingwindow;
//Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
//
//
//
//Example 1:
//
//Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//Output: 6
//Explanation: [1,1,1,0,0,1,1,1,1,1,1]
//Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//Example 2:
//
//Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
//Output: 10
//Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//
//
//Constraints:
//
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.
//0 <= k <= nums.length
/// /Instead of thinking about literally "flipping" zeros, rephrase the problem like
/// this:Find the length of the longest contiguous subarray that contains at most $k$ zeros.
class MaxConsecutiveOnesIII{
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // If we encounter a 0, record it in our count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If our zero budget is exhausted, shrink the window from the left
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--; // We successfully kicked a 0 out of the window
                }
                left++; // Move the left boundary forward
            }

            // Calculate the current valid window length and update maxLen
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        MaxConsecutiveOnesIII solution = new MaxConsecutiveOnesIII();
        int result = solution.longestOnes(nums, k);
        System.out.println("Maximum number of consecutive 1's: " + result); // Output
    }
}