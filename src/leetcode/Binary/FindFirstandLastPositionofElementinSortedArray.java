package leetcode.Binary;
//Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
//
//If target is not found in the array, return [-1, -1].
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//Example 2:
//
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
//Example 3:
//
//Input: nums = [], target = 0
//Output: [-1,-1]
//
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);

        return result;
    }

    private int findFirst(int[] nums, int target) {
        int index = -1;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                if (nums[mid] == target) index = mid;
                high = mid - 1; // Keep looking left
            } else {
                low = mid + 1;
            }
        }
        return index;
    }

    private int findLast(int[] nums, int target) {
        int index = -1;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= target) {
                if (nums[mid] == target) index = mid;
                low = mid + 1; // Keep looking right
            } else {
                high = mid - 1;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        FindFirstandLastPositionofElementinSortedArray solution = new FindFirstandLastPositionofElementinSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = solution.searchRange(nums, target);
        System.out.println("First and Last Position of Target: [" + result[0] + ", " + result[1] + "]");
    }
}
