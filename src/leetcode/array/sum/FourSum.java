package leetcode.array.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array nums of n integers, return an array of all the unique quadruplets
// [nums[a], nums[b], nums[c], nums[d]] such that:
//
//0 <= a, b, c, d < n
//a, b, c, and d are distinct.
//nums[a] + nums[b] + nums[c] + nums[d] == target
//You may return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//Example 2:
//
//Input: nums = [2,2,2,2,2], target = 8
//Output: [[2,2,2,2]]
//
//
//Constraints:
//
//1 <= nums.length <= 200
//-109 <= nums[i] <= 109
//-109 <= target <= 109
public class FourSum {
    public static void main(String[] args) {
        FourSum solution = new FourSum();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> quadruplets = solution.fourSum(nums, target);
        System.out.println("Quadruplets that sum to target: " + quadruplets);
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            /* --- PRUNING OPTIMIZATIONS --- */
            // 1. If the smallest possible sum starting with nums[i] is > target, no need to continue
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // 2. If the largest possible sum starting with nums[i] is < target, skip this i
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                /* --- MORE PRUNING --- */
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Fix: Cast to long to prevent overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
