package leetcode.array.slidingwindow;

import java.util.HashSet;

//You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
//
//The length of the subarray is k, and
//All the elements of the subarray are distinct.
//Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
//
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//Example 1:
//
//Input: nums = [1,5,4,2,9,9,9], k = 3
//Output: 15
//Explanation: The subarrays of nums with length 3 are:
//- [1,5,4] which meets the requirements and has a sum of 10.
//- [5,4,2] which meets the requirements and has a sum of 11.
//- [4,2,9] which meets the requirements and has a sum of 15.
//- [2,9,9] which does not meet the requirements because the element 9 is repeated.
//- [9,9,9] which does not meet the requirements because the element 9 is repeated.
//We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
public class MaximumSumofDistinctSubarraysWithLengthK {
    public static long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long currentSum = 0;
        int left = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int right = 0; right < nums.length; right++) {
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            set.add(nums[right]);
            currentSum += nums[right];

            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        System.out.println(maximumSubarraySum(nums, k)); // Output: 12 (subarray [3,4,5])
    }
}
