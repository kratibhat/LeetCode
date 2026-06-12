package leetcode.array.slidingwindow;
//Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
//
//
//
//Example 1:
//
//Input: nums = [10,5,2,6], k = 100
//Output: 8
//Explanation: The 8 subarrays that have product less than 100 are:
//[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
//Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
//Example 2:
//
//Input: nums = [1,2,3], k = 0
//Output: 0
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Edge case: since elements are positive integers,
        // a product can never be strictly less than 1 or 0.
        if (k <= 1) return 0;

        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // Include the current element in the product
            product *= nums[right];

            // Shrink the window from the left if the product is too large
            while (product >= k) {
                product /= nums[left];
                left++;
            }

            // All subarrays ending at 'right' and starting from 'left' up to 'right' are valid
            count += (right - left + 1);
        }

        return count;
    }
    public static void main(String[] args) {
        SubarrayProductLessThanK solution = new SubarrayProductLessThanK();
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        int result = solution.numSubarrayProductLessThanK(nums, k);
        System.out.println("Number of subarrays with product less than " + k + ": " + result);
    }
}
