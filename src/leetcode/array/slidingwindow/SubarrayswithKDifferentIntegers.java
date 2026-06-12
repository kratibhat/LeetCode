package leetcode.array.slidingwindow;

import java.util.HashMap;

//Given an integer array nums and an integer k, return the number of good subarrays of nums.
//
//A good array is an array where the number of different integers in that array is exactly k.
//
//For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
//A subarray is a contiguous part of an array.
//
//
//
//Example 1:
//
//Input: nums = [1,2,1,2,3], k = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
//Example 2:
//
//Input: nums = [1,2,1,3,4], k = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
//
public class SubarrayswithKDifferentIntegers {
    public int subarraysWithKDistinct1(int[] nums, int k) {
        // Exactly(k) = AtMost(k) - AtMost(k - 1)
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // Helper function to find the number of subarrays with AT MOST 'goal' distinct integers
    private int atMostK(int[] nums, int goal) {
        if (goal == 0) return 0;

        HashMap<Integer, Integer> counts = new HashMap<>();
        int left = 0;
        int totalSubarrays = 0;

        for (int right = 0; right < nums.length; right++) {
            // Add current number to our window frequency map
            counts.put(nums[right], counts.getOrDefault(nums[right], 0) + 1);

            // If unique elements exceed our goal, shrink the window from the left
            while (counts.size() > goal) {
                counts.put(nums[left], counts.get(nums[left]) - 1);
                if (counts.get(nums[left]) == 0) {
                    counts.remove(nums[left]); // Completely erase to reduce map size
                }
                left++;
            }

            // Number of valid subarrays ending at 'right' is equal to window length
            totalSubarrays += (right - left + 1);
        }

        return totalSubarrays;
    }
    public int subarraysWithKDistinctOptimal(int[] nums, int k) {
        // Array to store the count of distinct values encountered
        int[] distinctCount = new int[nums.length + 1];

        int totalCount = 0;
        int left = 0;
        int right = 0;
        int currCount = 0;

        while (right < nums.length) {
            // Increment the count of the current element in the window
            if (distinctCount[nums[right++]]++ == 0) {
                // If encountering a new distinct element, decrement K
                k--;
            }

            // If K becomes negative, adjust the window from the left
            while (k < 0) {
                // Move the left pointer until the count of distinct elements becomes valid again
                --distinctCount[nums[left++]];
                k++;
                currCount = 0;
            }

            // If K becomes zero, calculate subarrays
            if (k == 0) {
                // While the count of left remains greater than 1, keep shrinking the window from the left
                while (distinctCount[nums[left]] > 1) {
                    --distinctCount[nums[left++]];
                    currCount++;
                }
                // Add the count of subarrays with K distinct elements to the total count
                totalCount += (currCount + 1);
            }
        }
        return totalCount;
    }
    public static void main(String[] args) {
        SubarrayswithKDifferentIntegers solution = new SubarrayswithKDifferentIntegers();
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        int result = solution.subarraysWithKDistinct1(nums, k);
        System.out.println("Number of good subarrays: " + result); // Output: 7
    }
}
