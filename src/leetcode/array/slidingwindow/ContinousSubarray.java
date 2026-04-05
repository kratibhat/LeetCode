package leetcode.array.slidingwindow;
//You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:
//
//Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
//Return the total number of continuous subarrays.
//
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//Example 1:
//
//Input: nums = [5,4,2,4]
//Output: 8
//Explanation:
//Continuous subarray of size 1: [5], [4], [2], [4].
//Continuous subarray of size 2: [5,4], [4,2], [2,4].
//Continuous subarray of size 3: [4,2,4].
//There are no subarrys of size 4.
//Total continuous subarrays = 4 + 3 + 1 = 8.
//It can be shown that there are no more continuous subarrays.
public class ContinousSubarray {
    public long continuousSubarrays(int[] nums) {
        /// /2ms
        int n = nums.length;
        int start =0;
        int end = 0;
        int currMin = Integer.MAX_VALUE;
        int currMax = Integer.MIN_VALUE;
        long count =0;
//Step 1: Identify the "Core Shape"The first thing you do is look at what you are being asked
// for.Keywords: "Subarray," "Contiguous," "Total number of..."Mental Tool: This is almost always
// a Sliding Window.Why? Because you are looking for a "chunk" of the array that moves.
// Step 2: Define the "Breaking Point"Ask yourself: "What makes a window go from 'Good' to 'Bad'?
// "The Symptom: Any two numbers can't differ by more than 2.The Logic: This is impossible to track
// for every single pair. You must simplify it.
// A window is only "Bad" if the Current Max - Current Min > 2.
// Mental Tool: Tracking boundaries. You need a way to know the min and max of your window at all
// times.Step 3: Choose your "Optimization" (The 2ms vs 20ms decision)Now you need to decide how to
// fix a "Bad" window.The "Brute" thought: "If it breaks, I'll restart the start pointer and move it
// one by one." (Too slow, $O(N^2)$).The "Heavy" thought: "I'll use a TreeMap or Deques to keep the
// Min/Max." (Good, but complex to code).The "Greedy/Fast" thought (Your code): "The limit is only 2.
// If it breaks, the new window must include the current element. I'll just walk backwards from
// the current element to see how many previous neighbors are still 'friendly' (within 2) of this new
// number."Step 4: Use the "Accounting" FormulaFinally, you need to count them.The Problem:
// How do I count [5, 4, 2] without double-counting [5, 4]?The Rule: Don't count "everything in
// the window." Only count "subarrays ending at the current end."Mental Tool: end - start + 1.
// This is the universal formula for counting subarrays ending at a specific point.
        while(end<n)
        {
            currMax = Math.max(currMax,nums[end]);
            currMin = Math.min(currMin,nums[end]);
            if(currMax-currMin>2)
            {
                start=end;
                currMax = nums[end];
                currMin = nums[start];
                while(start-1>=0 && Math.abs(nums[start-1]-nums[end])<=2)
                {
                    start--;
                    currMin = Math.min(currMin,nums[start]);
                    currMax = Math.max(currMax,nums[start]);
                }
            }

            count += end-start+1;
            end++;
        }
        return count;
    }
    public static void main(String[] args) {
        ContinousSubarray solution = new ContinousSubarray();
        int[] nums = {5, 4, 2, 4};
        long result = solution.continuousSubarrays(nums);
        System.out.println("Total Continuous Subarrays: " + result); // Output: 8
    }
}
