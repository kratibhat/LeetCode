package leetcode.Binary.BSonAnswers;
//Given an array of integers nums and an integer threshold, we will choose a positive integer
// divisor, divide all the array by it, and sum the division's result. Find the smallest divisor
// such that the result mentioned above is less than or equal to threshold.
//
//Each result of the division is rounded to the nearest integer greater than or equal to that
// element. (For example: 7/3 = 3 and 10/2 = 5).
//
//The test cases are generated so that there will be an answer.
//
//
//
//Example 1:
//
//Input: nums = [1,2,5,9], threshold = 6
//Output: 5
//Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
//If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
//Example 2:
//
//Input: nums = [44,22,33,11,1], threshold = 5
//Output: 44
public class FindtheSmallestDivisorGivenaThreshold {
    public int smallestDivisor(int[] nums, int threshold) {

        int low = 1;
        int high = 0;

        // Find maximum number
        for (int num : nums) {
            high = Math.max(high, num);
        }

        while (low < high) {

            int mid = low + (high - low) / 2;

            int sum = 0;

            for (int num : nums) {
                // ceil(num / mid)
                sum += (num + mid - 1) / mid;
            }

            if (sum <= threshold) {
                high = mid;          // divisor works, try smaller
            } else {
                low = mid + 1;       // divisor too small
            }
        }

        return low;
    }
    public static void main(String [] args){
        int threshold=6;
        int [] arr={1,2,5,9};
        FindtheSmallestDivisorGivenaThreshold solution = new FindtheSmallestDivisorGivenaThreshold();
        System.out.println(solution.smallestDivisor(arr, threshold));
    }
}
//✅ Koko Eating Bananas
//✅ Minimum Days to Make m Bouquets
//✅ Find the Smallest Divisor Given a Threshold
//✅ Capacity to Ship Packages Within D Days
//✅ Split Array Largest Sum
//✅ Aggressive Cows / Magnetic Force Between Two Balls
//✅ Minimize Max Distance to Gas Station
//✅ Allocate Minimum Number of Pages
//✅ Painter's Partition Problem