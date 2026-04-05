package leetcode.arraystuf;

import java.util.Arrays;
//The frequency of an element is the number of times it occurs in an array.
//
//You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
//
//Return the maximum possible frequency of an element after performing at most k operations.
//
//
//
//Example 1:
//
//Input: nums = [1,2,4], k = 5
//Output: 3
//Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
//4 has a frequency of 3.
//Example 2:
//
//Input: nums = [1,4,8,13], k = 5
//Output: 2
//Explanation: There are multiple optimal solutions:
//- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
//- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
//- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
public class FrequencyOftheMostFrequentElement {
    public int maxFrequency(int[] nums, int k) {
        // Step 1: Standard Sort (Safe for values up to 10^9)
        Arrays.sort(nums);

        int n = nums.length;
        int left = 0;
        long windowSum = 0;

        // Step 2: Non-shrinking Sliding Window
        for (int right = 0; right < n; right++) {
            windowSum += nums[right];

            // Condition: (Target * WindowSize) - Sum > k
            // We use 'if' instead of 'while' to keep the window size
            // at the maximum value it has ever reached.
            //if loop indicates 2*2 =4-3=1 > 5 false
            //multiply number of times we want to increment with the target
            // value and then subtract the current sum of the window.
            // If this value exceeds k, it means we cannot achieve the
            // desired frequency with the current window size, and we
            // need to move the left pointer to reduce the window size.
            if ((long) nums[right] * (right - left + 1) - windowSum > k) {
                windowSum -= nums[left];
                left++;
            }
        }

        // Step 3: The result is simply the size of the final window
        return n - left;
    }
    public static void main(String[] args) {
        FrequencyOftheMostFrequentElement solution = new FrequencyOftheMostFrequentElement();
        int[] nums = {1, 2, 4};
        int k = 5;
        int result = solution.maxFrequency(nums, k);
        System.out.println("Maximum frequency of the most frequent element: " + result); // Output: 3
    }
}
//Example Input:
//nums = [1, 2, 4, 8], k = 5
//
//Step 1: Initialize
//Sorted Array: [1, 2, 4, 8]
//Variables:
//left = 0
//windowSum = 0
//max_freq = 0
//
//Step 2: Iteration 1 (right = 0)
//New element: 1
//windowSum: 0 + 1 = 1
//Calculation: (1 * 1) - 1 = 0
//Logic: 0 <= 5 (Valid)
//Window: [1] (Size 1)
//
//Step 3: Iteration 2 (right = 1)
//New element: 2
//windowSum: 1 + 2 = 3
//Calculation: (2 * 2) - 3 = 1
//Logic: 1 <= 5 (Valid)
//We need 1 operation to turn [1, 2] into [2, 2]
//Window: [1, 2] (Size 2)
//
//Step 4: Iteration 3 (right = 2)
//New element: 4
//windowSum: 3 + 4 = 7
//Calculation: (4 * 3) - 7 = 5
//Logic: 5 <= 5 (Valid)
//We need 5 operations to turn [1, 2, 4] into [4, 4, 4]
//1 -> 4 (3 ops)
//2 -> 4 (2 ops)
//Total = 5
//Window: [1, 2, 4] (Size 3)
//
//Step 5: Iteration 4 (right = 3)
//New element: 8
//windowSum: 7 + 8 = 15
//Calculation: (8 * 4) - 15 = 17
//Logic: 17 > 5 (Invalid)
//
//We don't have enough k to turn [1, 2, 4, 8] into [8, 8, 8, 8]
//
//The "Shift" (if block):
//Subtract nums[left] (which is 1) from windowSum
//windowSum = 15 - 1 = 14
//Move left to index 1
//
//Window: [2, 4, 8] (Size remains 3)
//
//Final Result:
//After the loop finishes, the code returns n - left
//
//n = 4
//left = 1
//
//Result: 4 - 1 = 3
//
//The maximum frequency found was 3 (the window [1, 2, 4])