package leetcode.array.KadaneAlgo;

public class MaximumAbsoluteSumofAnySubarray {
    public int maxAbsoluteSum(int[] nums) {
        int maxSoFar = 0;
        int minSoFar = 0;

        int maxEndingHere = 0;
        int minEndingHere = 0;

        for (int num : nums) {
            // Track the maximum positive subarray streak
            maxEndingHere += num;
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            if (maxEndingHere < 0) {
                maxEndingHere = 0; // Reset tracking if it becomes negative
            }

            // Track the minimum negative subarray streak
            minEndingHere += num;
            minSoFar = Math.min(minSoFar, minEndingHere);
            if (minEndingHere > 0) {
                minEndingHere = 0; // Reset tracking if it becomes positive
            }
        }

        // The maximum absolute value will be the larger of the two global extremes
        return Math.max(maxSoFar, Math.abs(minSoFar));
    }
    public static void main(String[] args) {
        MaximumAbsoluteSumofAnySubarray solution = new MaximumAbsoluteSumofAnySubarray();
        int[] nums = {2,-5,1,-4,3,-2};
        int result = solution.maxAbsoluteSum(nums);
        System.out.println("Maximum Product Subarray: " + result); // Output: 6
    }
}
