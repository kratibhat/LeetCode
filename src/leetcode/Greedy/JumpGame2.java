package leetcode.Greedy;
//You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
//
//Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
//
//0 <= j <= nums[i] and
//i + j < n
//Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
//
//
//
//Example 1:
//
//Input: nums = [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//Example 2:
//
//Input: nums = [2,3,0,1,4]
//Output: 2
//
//
//Constraints:
//
//1 <= nums.length <= 104
//0 <= nums[i] <= 1000
//It's guaranteed that you can reach nums[n - 1].
public class JumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
//Why currentEnd is the boundary: In the table above, when i = 1, we saw that we could reach index 4.
// But we didn't increase our jumps yet! We waited until we reached i = 2 (the boundary of our first
// jump) because we wanted to see if any other index (like index 1 or 2) gave us an even better reach.
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
    public static void main(String[] args) {
        JumpGame2 solution = new JumpGame2();
        int[] nums = {2, 3, 1, 1, 4};// first 2 window will be from 0 to

        //will check wch jump is better 1 or 2
        //here 1 is better so jump to index 1

        int result = solution.jump(nums);
        System.out.println("Minimum number of jumps: " + result); // Output: 2
    }
}
//farthest: This is the furthest island you can possibly see/reach from your current island and any other island you have already passed. You are constantly updating this as you walk.
//
//currentEnd: This is the "boundary" of your current jump.
// Once you reach this boundary, you must make another jump to continue.
//
//jumps: The counter for how many times you've actually leaped.