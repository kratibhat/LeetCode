package leetcode.array.easy;
//Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//You must write an algorithm that runs in O(n) time and without using the division operation.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3,4]
//Output: [24,12,8,6]
//Example 2:
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int [] right = new int[n];
//LOGIC IS TO CREATE TWO ARRAYS LEFT AND RIGHT WHERE LEFT[I] WILL HAVE PRODUCT OF ALL ELEMENTS TO THE LEFT OF INDEX I AND RIGHT[I] WILL HAVE PRODUCT OF ALL ELEMENTS TO THE RIGHT OF INDEX I.
// FINALLY MULTIPLY LEFT AND RIGHT ARRAYS TO GET THE OUTPUT.
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] output = new int[n];
        for (int i = 0; i < n; i++) {
            output[i] = left[i] * right[i];
        }
        return output;
    }
    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();
        int[] nums = {2, 2, 3, 4};
        int[] result = solution.productExceptSelf(nums);
        // Expected output: [24, 12, 8, 6]
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
