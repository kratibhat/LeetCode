package leetcode.Binary;

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
