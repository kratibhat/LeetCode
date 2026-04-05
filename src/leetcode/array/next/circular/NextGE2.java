package leetcode.array.next.circular;
//Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
//
//The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
//
//
//
//Example 1:
//
//Input: nums = [1,2,1]
//Output: [2,-1,2]
//Explanation: The first 1's next greater number is 2;
//The number 2 can't find next greater number.
//The second 1's next greater number needs to search circularly, which is also 2.
//Example 2:
//
//Input: nums = [1,2,3,4,3]
//Output: [2,3,4,-1,4]
//
public class NextGE2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // Manual stack using an array and a pointer
        int[] stack = new int[n * 2];
        int top = -1;

        // Iterate backwards from 2n - 1 to 0
        for (int i = 2 * n - 1; i >= 0; i--) {
            int currentVal = nums[i % n];

            // Standard Monotonic Stack logic: Pop smaller elements
            while (top >= 0 && nums[stack[top]] <= currentVal) {
                top--;
            }

            // Only fill the answer array during the "actual" first pass
            if (i < n) {
                ans[i] = (top == -1) ? -1 : nums[stack[top]];
            }

            // Push the current index onto our array-stack
            stack[++top] = i % n;
        }

        return ans;
    }
    public static void main(String[] args) {
        NextGE2 solution = new NextGE2();
        int[] nums = {1, 2, 1};
        int[] result = solution.nextGreaterElements(nums);
        System.out.println(java.util.Arrays.toString(result)); // Output: [2, -1, 2]
    }
}
//Need nearest taller person? -> Use a Stack.
//
//Need to wrap around? -> Loop twice (2 * n).
//
//Need to see the right side? -> Walk backwards.
//
//Current person is taller than the stack? -> Pop the stack.