package leetcode.array.prefixsumandhashing;

import java.util.*;

//Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
//
//A good subarray is a subarray where:
//
//its length is at least two, and
//the sum of the elements of the subarray is a multiple of k.
//Note that:
//
//A subarray is a contiguous part of the array.
//An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
//Example 1:
//
//Input: nums = [23,2,4,6,7], k = 6
//Output: true
//Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
//Example 2:
//
//Input: nums = [23,2,6,4,7], k = 6
//Output: true
//Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
//42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
//Example 3:
//
//Input: nums = [23,2,6,4,7], k = 13
//Output: false
//
public class ContinousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) return false;
//the remainder can only be:
//
//0, 1, 2, ..., k-1
        Map<Integer, Integer> map = new HashMap<>(Math.min(nums.length, k));
        map.put(0, -1);

        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            int remainder = runningSum % k;

            Integer prevIndex = map.putIfAbsent(remainder, i);

            if (prevIndex != null) {
                if (i - prevIndex >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        ContinousSubarraySum solution = new ContinousSubarraySum();
        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        System.out.println(solution.checkSubarraySum(nums1, k1)); // Output: true

        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;
        System.out.println(solution.checkSubarraySum(nums2, k2)); // Output: true

        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;
        System.out.println(solution.checkSubarraySum(nums3, k3)); // Output: false
    }
}
////*
/// STEP 1: Prefix Sum Definition
/// -----------------------------
/// prefixSum[i] = sum of elements from index 0 to i
///
/// Sum of subarray (prevIndex + 1) to i:
/// subarraySum = prefixSum[i] - prefixSum[prevIndex]
///
///
/// STEP 2: Same Remainder Modulo k
/// -------------------------------
/// If two prefix sums have the same remainder when divided by k:
///
/// prefixSum[i] % k == prefixSum[prevIndex] % k
///
/// Then there exist integers a, b, and remainder r such that:
///
/// prefixSum[i]        = a * k + r
/// prefixSum[prevIndex]= b * k + r
///
/// (both have the same remainder r)
///
///
/// STEP 3: Subtract Prefix Sums
/// ----------------------------
/// prefixSum[i] - prefixSum[prevIndex]
///
/// Substitute values:
///
/// (a * k + r) - (b * k + r)
/// = (a - b) * k
///
///
/// STEP 4: Take Modulo k
/// ---------------------
/// (prefixSum[i] - prefixSum[prevIndex]) % k
/// = ((a - b) * k) % k
/// = 0
///
///
/// FINAL CONCLUSION
/// ----------------
/// (prefixSum[i] - prefixSum[prevIndex]) % k == 0
///
/// => The subarray sum is exactly divisible by k
/// => The subarray sum is a multiple of k
///
/// This is why tracking (runningSum % k) works.
/// */





////4. Why map.put(0, -1)?This is a "dummy entry" to handle subarrays that start from Index 0.
//If your runningSum becomes 12 and $k=6$ at index 1, the remainder is 0.The math i - prevIndex
// becomes 1 - (-1) = 2.This correctly identifies that the subarray nums[0...1] is a multiple of $k$
// and has a length of 2.

///*
//Key Idea: Prefix Sum + Modulo (Sum % k == 0)
//
//1) Why Modulo?
//   - If two prefix sums have the same remainder modulo k,
//     their difference is divisible by k.
//   - This helps us detect subarrays whose sum is a multiple of k.
//
//2) Prefix Sum Condition
//   - We track runningSum % k at each index.
//   - If the same remainder appears again, the subarray between
//     the two indices has sum % k == 0.
//
//3) Length Constraint (>= 2)
//   - We store the FIRST index where a remainder appears.
//   - When we see it again at index i, we check:
//       i - prevIndex >= 2
//
//4) Why HashMap Stores Index
//   - remainder -> earliest index
//   - This allows distance checking to satisfy the length condition.
//
//5) Handles Negative Numbers
//   - Unlike Sliding Window, this approach works with negatives.
//   - We normalize modulo using:
//       remainder = (remainder + k) % k
//
//6) Efficiency
//   - Single pass over the array
//   - Time Complexity: O(n)
//   - Space Complexity: O(k) (or O(n) in worst case)
//*
//
//
//
// /