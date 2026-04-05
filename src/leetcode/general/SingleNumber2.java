package leetcode.general;
//Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
//
//You must implement a solution with a linear runtime complexity and use only constant extra space.
public class SingleNumber2 {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }
public static void main(String[] args) {
    SingleNumber2 solution = new SingleNumber2();
    int[] nums = {2, 2, 3, 2};
    int result = solution.singleNumber(nums);
    System.out.println("Single Number: " + result); // Output: 3`
}
}
