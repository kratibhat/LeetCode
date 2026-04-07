package leetcode.arraystuf;

import java.util.HashMap;
import java.util.Map;

//Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            // Get the complement using the target value
            int complement = target - nums[i];

            // Search the hashmap for complement, if found, we got our pair
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Put the element in hashmap for subsequent searches.
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    } // Return -1, -1 if no solution is found

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]"); // Output: [0, 1]
    }
}
