package leetcode.array.KadaneAlgo;
/// /Given a circular integer array nums of length n, return the maximum possible
/// sum of a non-empty subarray of nums.
///
/// A circular array means the end of the array connects to the beginning of the array.\
// Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element
// of nums[i] is nums[(i - 1 + n) % n].
///
// A subarray may only include each element of the fixed buffer nums at most once.
// Formally, for a subarray nums[i], nums[i+1], ..., nums[j], there does not exist i <= k1,
// k2 <= j with k1 % n == k2 % n.
///
///
///
/// Example 1:
///
/// Input: nums = [1,-2,3,-2]
/// Output: 3
/// Explanation: Subarray [3] has maximum sum 3.
/// Example 2:
///
/// Input: nums = [5,-3,5]
/// Output: 10
/// Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
/// Example 3:
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircularoptimal(int[] arr) {
        int original=kadane(arr);
        if(original<0)
        {
            return original;
        }
        int total=0;
        for(int i=0;i<arr.length;i++)
        {
            total+=arr[i];
            arr[i]=-arr[i];
        }
        int inv=kadane(arr);
        int invsum=total+inv;
        return Math.max(invsum,original);
    }
    public int kadane(int[]arr)
    {
        int max=arr[0];
        int sum=0;
        for (int j : arr) {
            sum += j;
            if (max < sum) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
    public int maxSubarraySumCircular8ms(int[] nums) {
        int totalSum = 0;

        // Tracks for Maximum Subarray (Standard Kadane's)
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        // Tracks for Minimum Subarray
        int minSoFar = nums[0];
        int minEndingHere = nums[0];

        totalSum += nums[0];

        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i];

            // Standard Kadane's to find max subarray
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);

            // Inverted Kadane's to find min subarray
            minEndingHere = Math.min(nums[i], minEndingHere + nums[i]);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }

        // Edge case: If all elements are negative, return maxSoFar directly
        if (maxSoFar < 0) {
            return maxSoFar;
        }

        // Return the better choice between standard and wrapped subarrays
        return Math.max(maxSoFar, totalSum - minSoFar);
    }
    public static void main(String [] args)
    {
        MaximumSumCircularSubarray solution = new MaximumSumCircularSubarray();
        int[] nums1 = {5,-3,5};
        int result1 = solution.maxSubarraySumCircularoptimal(nums1);
        System.out.println("Maximum Sum Circular Subarray: " + result1); // Output: 3

    }

}
