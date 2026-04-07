package leetcode.arraystuf;
//Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//
//You must implement a solution with a linear runtime complexity and use only constant extra space.
//
//Input: nums = [2,2,1]
//
//Output: 1
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
    public static void main(String[] args){
        SingleNumber solution = new SingleNumber();
        int[] nums = {2,2,1};
        int single = solution.singleNumber(nums);
        System.out.println("Single Number: " + single); // Output: 1
    }
}
