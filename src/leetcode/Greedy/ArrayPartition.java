package leetcode.Greedy;

import java.util.Arrays;

//Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
//
//
//
//Example 1:
//
//Input: nums = [1,4,3,2]
//Output: 4
//Explanation: All possible pairings (ignoring the ordering of elements) are:
//1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
//2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
//3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
//So the maximum possible sum is 4.
public class ArrayPartition {

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
    public int arrayPairSumOptimal(int[] nums) {
        int mini=0;
        int maxi=0;
        for(int i:nums){
            mini=Math.min(mini,i);
            maxi=Math.max(maxi,i);
        }
        int[] freq=new int[maxi-mini+2];
        for(int i:nums){
            freq[i-mini]++;
        }
        int sum=0;
        boolean flag=true;
        for(int i=0;i<freq.length;i++){
            while(freq[i]!=0){
                if(flag){
                    sum+=(i+mini);
                }
                flag=!flag;
                freq[i]--;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        ArrayPartition solution = new ArrayPartition();
        int[] nums = {1, 4, 3, 2};
        int result = solution.arrayPairSum(nums);
        System.out.println("Maximized sum of min(ai, bi): " + result); // Output: 4
    }
}
