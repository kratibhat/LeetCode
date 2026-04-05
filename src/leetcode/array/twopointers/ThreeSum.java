package leetcode.array.twopointers;
//Given an integer array nums, return all the triplets [nums[i],
// nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//Notice that the solution set must not contain duplicate triplets.
//
//
//
//Example 1:
//
//Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
//Explanation:
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//The distinct triplets are [-1,0,1] and [-1,-1,2].
//Notice that the order of the output and the order of the triplets does not matter.
import java.util.*;

//hashset is used because we want to avoid duplicate triplets in the result
public class ThreeSum {
    // Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    // Notice that the solution set must not contain duplicate triplets.

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums); // Sort the array to use two-pointer technique

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(result);
    }
    public List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // O(n log n)
/// /DUPLICATE AVOIDANCE AND EARLY TERMINATION
        for (int i = 0; i < nums.length - 2; i++) {
            // Optimization 1: Skip the same element for the 'i' position
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Optimization 2: If the smallest possible sum is > 0, break
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for the 'left' pointer
                    //to avoid counting the same triplet multiple times
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for the 'right' pointer
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> triplets = solution.threeSumOptimal(nums);
        System.out.println("Triplets that sum to zero: " + triplets);
    }
}
//Check	When it happens	Purpose
//Skip i	-->At the start of the outer loop.	Prevents duplicate triplets that start with the same number.
//Skip left / right	-->>>Immediately after finding a sum of 0.	Prevents duplicate triplets that have the same middle and end numbers for a fixed start.