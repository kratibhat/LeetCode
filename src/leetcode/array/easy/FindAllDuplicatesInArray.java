package leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

//442. Find All Duplicates in an Array
//Medium
//Topics
//premium lock icon
//Companies
//Given an integer array nums of length n where all the integers of nums are
// in the range [1, n] and each integer appears at most twice,
// return an array of all the integers that appears twice.
//
//You must write an algorithm that runs in O(n) time and uses
// only constant auxiliary space, excluding the space needed to store the output
//
//
//
//Example 1:
//
//Input: nums = [4,3,2,7,8,2,3,1]
//Output: [2,3]
public class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        FindAllDuplicatesInArray solution = new FindAllDuplicatesInArray();
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> duplicates = solution.findDuplicates(nums);
        System.out.println("Duplicates: " + duplicates); // Output: [2, 3]
    }
}
