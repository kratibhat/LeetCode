package leetcode.array.KadaneAlgo;
//53. Maximum Subarray--kadane's algorithm
//Medium
//Topics
//premium lock icon
//Companies
//Given an integer array nums, find the subarray with the largest sum, and return its sum.
//
//
//
//Example 1:
//
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//Example 2:
//
//Input: nums = [1]
//Output: 1
//Explanation: The subarray [1] has the largest sum 1
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
    public int maxSubArrayoptimal(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curr_sum = Integer.MIN_VALUE;
        for(int i : nums){
            if(curr_sum<=0) curr_sum=i;
            else {
                curr_sum+=i;
            }
            max = max<curr_sum?curr_sum:max;
        }
        return max;
    }
    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int result = solution.maxSubArray(nums);
        System.out.println("Maximum Subarray Sum: " + result); // Output: 6
    }

}
