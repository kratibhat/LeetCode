package leetcode.arraystuf;
//Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.
//
//There may be duplicates in the original array.
//
//Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.
//
//
//
//Example 1:
//
//Input: nums = [3,4,5,1,2]
//Output: true
//Explanation: [1,2,3,4,5] is the original sorted array.
//You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].
//Example 2:
//
//Input: nums = [2,1,3,4]
//Output: false
//Explanation: There is no sorted array once rotated that can make nums.
//Example 3:
//
//Input: nums = [1,2,3]
//Output: true
//Explanation: [1,2,3] is the original sorted array.
//You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
public class CheckifArrayIsSortedandRotated {
    public boolean check(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[(i + 1) % nums.length]) {
                count++;
            }
        }
        return count <= 1;
    }
    public static void main(String[] args) {
        CheckifArrayIsSortedandRotated solution = new CheckifArrayIsSortedandRotated();
        int[] nums1 = {3, 4, 5, 1, 2};
        int[] nums2 = {2, 1, 3, 4};
        int[] nums3 = {1, 2, 3};

        System.out.println(solution.check(nums1)); // Output: true
        System.out.println(solution.check(nums2)); // Output: false
        System.out.println(solution.check(nums3)); // Output: true
    }
}
//1. The "Drop" Condition
//In a non-decreasing sorted array, every element should be less than or equal to the next one: nums[i] <= nums[i+1].
//
//If nums[i] > nums[i+1], it means the sorted order is broken. We call this a "violation" or a "drop."
//
//2. The Circular Check: (i + 1) % nums.length
//The most important part of this code is the modulo operator (%). This allows the loop to treat the array as a circle.
//
//When the loop reaches the very last element, (i + 1) % nums.length wraps back around to index 0.
//
//This checks if the last element is smaller than or equal to the first element.
//
//Why check the wrap-around?
//If an array is sorted and then rotated, the last element of the rotated array must be less than or equal to the first element to maintain the "circular" sorted property.
//
//3. The Logic of count <= 1
//If count == 0: The array is perfectly sorted and not rotated (e.g., [1, 2, 3]).
//
//If count == 1: The array was sorted and rotated once (e.g., [3, 4, 1, 2]). The only drop is from 4 to 1.
//
//If count > 1: The array is not sorted or has been modified in a way that rotation cannot fix (e.g., [3, 4, 1, 2, 0]). There are drops from 4 \rightarrow 1 and 2 \rightarrow 0.