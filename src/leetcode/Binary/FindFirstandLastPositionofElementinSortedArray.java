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
    public static void main(String[] args) {
        FindFirstandLastPositionofElementinSortedArray solution = new FindFirstandLastPositionofElementinSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = solution.searchRange(nums, target);
        System.out.println("First and Last Position of Target: [" + result[0] + ", " + result[1] + "]");
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);

        return result;
    }

    private int findFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int index = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid; // Found target
                high = mid - 1; // Look for an earlier one
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else { // nums[mid] > target
                high = mid - 1;
            }
        }

        return index;
    }

    private int findLast(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int index = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid; // Found target
                low = mid + 1; // Look for a later one
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else { // nums[mid] > target
                high = mid - 1;
            }
        }

        return index;
    }
}
