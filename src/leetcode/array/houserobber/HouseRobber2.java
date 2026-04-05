package leetcode.array.houserobber;
//You are a professional robber planning to rob houses along a street.
// Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
// Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
//
//
//
//Example 1:
//
//Input: nums = [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
public class HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robLinear(nums, 0, nums.length - 2),
                        robLinear(nums, 1, nums.length - 1));
    }
    private int robLinear(int[] nums, int start, int end) {
        int prev1 = 0; // max amount robbed up to the previous house
        int prev2 = 0; // max amount robbed up to the house before the previous house

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }


        public static void main(String[] args) {
        HouseRobber2 solution = new HouseRobber2();
        int[] nums = {2, 3, 2};
        int maxRobbedAmount = solution.rob(nums);
        System.out.println("Maximum Robbed Amount: " + maxRobbedAmount); // Output: 3
        }

}
