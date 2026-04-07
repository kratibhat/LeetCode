package leetcode.arraystuf;

import java.util.HashMap;
import java.util.Map;

//Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
//
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//Example 1:
//
//Input: nums = [1,1,1], k = 2
//Output: 2
//Example 2:
//
//Input: nums = [1,2,3], k = 3
//Output: 2
public class SubarraySumEqualsK {
    int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> sumCountMap = new HashMap<>();
        sumCountMap.put(0, 1);

        int result = 0;
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            if (sumCountMap.containsKey(prefixSum - k)) {
                result += sumCountMap.get(prefixSum - k);
            }

            sumCountMap.put(prefixSum,
                    sumCountMap.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }
    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        int[] nums = {1, 1, 1};
        int k = 2;
        int result = solution.subarraySum(nums, k);
        System.out.println("Number of subarrays with sum equals to " + k + ": " + result); // Output: 2
    }

}
