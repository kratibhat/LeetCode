package leetcode.Greedy;
// //You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
//At index i:
//
//You are currently standing on position i
//
//nums[i] tells you the maximum number of steps you can jump forward
//
//So the furthest you can reach from position i is:
//
//i (current position) + nums[i] (max jump length)
//
//
//This equals the furthest index reachable when jumping from i.
//consider 0 index 2 jumps 0+2=2 so from index 0 we can reach index 2
//consider 1 index 3 jumps 1+3=4 so from index 1 we can reach index 4
//consider 2 index 1 jumps 2+1=3 so from index 2 we can reach index 3
//consider 3 index 1 jumps 3+1=4 so from index 3 we can reach index 4
//Return true if you can reach the last index, or false otherwise.
//
//GREED
public class JumpGame {
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        int[] nums = {2, 3, 1, 1, 4};
        boolean canReachEnd = solution.canJump(nums);
        System.out.println("Can reach the last index: " + canReachEnd); // Output: true
    }
    public boolean canJump(int[] nums) {
        int maxReachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachable) {
                return false; // Can't reach this position
            }
            maxReachable = Math.max(maxReachable, i + nums[i]);
            if (maxReachable >= nums.length - 1) {
                return true; // Can reach or exceed the last index
            }
        }
        return false; // Just in case, though we should have returned earlier
    }
// Greedy Approach

    public boolean canJump1(int[] nums) {
        int n = nums.length;

        if(n==1) return true;

        int max = 0;

        for(int index=0;index<n-1 && max>=index;index++){
            if(max<index+nums[index]){
                max = index + nums[index];
            }

            if(max>=n-1){
                return true;
            }
        }

        return false;
    }
}
