package leetcode.Binary;
//There is an integer array nums sorted in ascending order (with distinct values).
//
//Prior to being passed to your function, nums is possibly left rotated
// at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
//
//Given the array nums after the possible rotation and an integer target,
// return the index of target if it is in nums, or -1 if it is not in nums.
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
public class SearchInARotatedSortedArray {
    public static int search(int[] nums, int target) {
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==target)
            {
                return i;
            }

        }
        return -1;
    }
    public int search1(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            // Step 1: Identify which half is sorted
            if (nums[low] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Target is in the sorted left half
                } else {
                    low = mid + 1;  // Target is in the right half
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;  // Target is in the sorted right half
                } else {
                    high = mid - 1; // Target is in the left half
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int result = search(nums, target);
        System.out.println("Index of target " + target + ": " + result);
    }
}
