package leetcode.Binary.BSonAnswers;
//Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
//
//Return the minimized largest sum of the split.
//
//A subarray is a contiguous part of the array.
//
//
//
//Example 1:
//
//Input: nums = [7,2,5,10,8], k = 2
//Output: 18
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
//Example 2:
//
//Input: nums = [1,2,3,4,5], k = 2
//Output: 9
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;

        // Search space
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canSplit(int[] nums, int k, int maxSum) {
        int count = 1;
        int currSum = 0;

        for (int num : nums) {
            if (currSum + num <= maxSum) {
                currSum += num;
            } else {
                count++;
                currSum = num;

                if (count > k) {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String []args){
        int []arr={7,2,5,10,8};
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        System.out.println(solution.splitArray(arr, 2));
    }
}
