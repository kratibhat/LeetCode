package leetcode.Binary;
//Given a sorted array arr[] and an integer target, find the number of occurrences of target in given array.
//
//Examples:
//
//Input: arr[] = [1, 1, 2, 2, 2, 2, 3], target = 2
//Output: 4
//Explanation: 2 occurs 4 times in the given array.
//
//Input: arr[] = [1, 1, 2, 2, 2, 2, 3], target = 4
//Output: 0
//Explanation: 4 is not present in the given array.
public class CountOccurrencesinaSortedArray {
    static int countFreq(int[] arr, int target) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {

            // If the current element is equal to
            // target, increment the result
            if (arr[i] == target)
                res++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 2, 3};
        int target = 2;
        System.out.println(countFreq(arr, target));
        System.out.println(countOccurrences(arr, target));

    }
    public static int countOccurrences(int[] nums, int target) {

        int first = findFirst(nums, target);

        if (first == -1)
            return 0;

        int last = findLast(nums, target);

        return last - first + 1;
    }

    public static int findFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int index = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                high = mid - 1;
            }
            else if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return index;
    }

    public static int findLast(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int index = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                low = mid + 1;
            }
            else if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return index;
    }
}
