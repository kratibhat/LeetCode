package leetcode.array.prefixsumandhashing;

public class SubarraySumsDivisiblebyK {
    public int subarraysDivByK(int[] nums, int k) {
        // Array to store the frequency of remainders [0 to k-1]
        int[] remainderCount = new int[k];

        // Base Case: A prefix sum of 0 has a remainder of 0, seen exactly once
        remainderCount[0] = 1;

        int runningSum = 0;
        int totalSubarrays = 0;

        for (int num : nums) {
            runningSum += num;

            // Normalize the remainder to handle negative numbers safely
            int remainder = (runningSum % k + k) % k;

            // If we have seen this remainder before, it means there are
            // matching historical sub-segments that form a valid divisible subarray
            totalSubarrays += remainderCount[remainder];

            // Increment the frequency of this remainder for future positions
            remainderCount[remainder]++;
        }

        return totalSubarrays;
    }
    public static void main(String[] args) {

        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;

        SubarraySumsDivisiblebyK obj = new SubarraySumsDivisiblebyK();

        int ans = obj.subarraysDivByK(nums, k);

        System.out.println(ans);
    }
}
