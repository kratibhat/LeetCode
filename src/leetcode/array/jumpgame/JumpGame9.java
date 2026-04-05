package leetcode.array.jumpgame;
//You are given an integer array nums.
//
//From any index i, you can jump to another index j under the following rules:
//
//Jump to index j where j > i is allowed only if nums[j] < nums[i].
//Jump to index j where j < i is allowed only if nums[j] > nums[i].
//For each index i, find the maximum value in nums that can be reached by following any sequence of valid jumps starting at i.
//
//Return an array ans where ans[i] is the maximum value reachable starting from index i.
//
//
//
//Example 1:
//
//Input: nums = [2,1,3]
//
//Output: [2,2,3]
public class JumpGame9 {
    public int[] maxValue(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        int[] preMax=new int[n];
        preMax[0]=nums[0];
        for(int i=1;i<n;i++){
            preMax[i]=Math.max(preMax[i-1],nums[i]);
        }
        int sufMin=Integer.MAX_VALUE;
        for(int i=n-1;i>=0;i--){
            if(preMax[i]>sufMin) ans[i]=ans[i + 1];
            else ans[i]=preMax[i];
            sufMin=Math.min(sufMin,nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        JumpGame9 solution = new JumpGame9();
        int[] arr = {2, 1, 3};

        int[] result = solution.maxValue(arr);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
