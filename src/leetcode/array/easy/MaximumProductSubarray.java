package leetcode.array.easy;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
    //Negative numbers can turn a minimum into a maximum → so track both

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap max and min when multiplied by a negative number
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);

            result = Math.max(result, maxProduct);
        }

        return result;
    }
    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        int[] nums = {2, 3, -2, 4};
        int result = solution.maxProduct(nums);
        System.out.println("Maximum Product Subarray: " + result); // Output: 6
    }
}
