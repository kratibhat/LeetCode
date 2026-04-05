package leetcode.array.jumpgame;

import java.util.ArrayDeque;
import java.util.Deque;

//You are given a 0-indexed integer array nums and an integer k.
//
//You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
//
//You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
//
//Return the maximum score you can get.
//
//
//
//Example 1:
//
//Input: nums = [1,-1,-2,4,-7,3], k = 2
//Output: 7
//Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
//Example 2:
//
//Input: nums = [10,-5,-2,4,0,3], k = 3
//Output: 17
//Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
public class JumpGame6 {
    public int maxResult(int[] nums, int k) {
        /// /5ms
        int n = nums.length, i, j, start = 0;
        int sum = nums[0];

        for(i=1; i<n; i++){
            nums[i] += nums[start];
            if(nums[i] >= nums[start]) start = i;

            if(i-start == k){
                start++;
                for(j = start+1; j<=i; j++){
                    if(nums[j] >= nums[start]) start = j;
                }
            }
        }

        return nums[n-1];

    }
    public static void main(String[] args) {
        JumpGame6 solution = new JumpGame6();
        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        int result = solution.maxResult(nums, k);
        System.out.println("Maximum Score: " + result); // Output: 7
    }
    public int maxResult1(int[] nums, int k) {
        /// /24ms
        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();

        dq.offerLast(0);

        for(int i=1;i<n;i++){

            nums[i] = nums[i] + nums[dq.peekFirst()];

            while(!dq.isEmpty() && nums[i]>=nums[dq.peekLast()]){
                dq.pollLast();
            }

            dq.offerLast(i);

            if(i - dq.peekFirst() >=k){
                dq.pollFirst();
            }

        }
        return nums[n-1];
    }
}
