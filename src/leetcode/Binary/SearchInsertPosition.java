package leetcode.Binary;
//Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [1,3,5,6], target = 5
//Output: 2
//Example 2:
//
//Input: nums = [1,3,5,6], target = 2
//Output: 1
//Example 3:
//Input: nums = [1,3,5,6], target = 7
//Output: 4
//Example 4:
//Input: nums = [1,3,5,6], target = 0
//Output: 0
public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        // Target not found, left is the insertion point
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int index = searchInsert(nums, target);
        System.out.println("Target " + target + " found/inserted at index: " + index);

        target = 2;
        index = searchInsert(nums, target);
        System.out.println("Target " + target + " found/inserted at index: " + index);

        target = 7;
        index = searchInsert(nums, target);
        System.out.println("Target " + target + " found/inserted at index: " + index);

        target = 0;
        index = searchInsert(nums, target);
        System.out.println("Target " + target + " found/inserted at index: " + index);
    }
}
/*
=========================================
BINARY SEARCH LOOP CONDITIONS
=========================================

1. while (low <= high)

Use when:
- Searching for an exact element.
- Need to check every possible index.
- The last remaining element (low == high) must also be examined.

Examples:
- Binary Search
- Search Insert Position
- Search in Rotated Sorted Array
- First Occurrence
- Last Occurrence

Template:

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] == target)
        return mid;

    // Move left or right
}

-----------------------------------------

2. while (low < high)

Use when:
- NOT searching for an exact value.
- Shrinking the search space until only ONE candidate remains.
- When low == high, that index is the answer.

Examples:
- Find Minimum in Rotated Sorted Array
- Find Peak Element (common solution)
- Lower/Upper bound variants (some implementations)

Template:

while (low < high) {
    int mid = low + (high - low) / 2;

    if (condition)
        low = mid + 1;
    else
        high = mid;   // or high = mid - 1 depending on the problem
}

return nums[low]; // or return low

=========================================
MEMORY TRICK
=========================================

Searching for a VALUE?
    -> while (low <= high)

Searching for a POSITION or narrowing to ONE answer?
    -> while (low < high)
*/