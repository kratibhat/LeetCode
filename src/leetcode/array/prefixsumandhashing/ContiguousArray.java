package leetcode.array.prefixsumandhashing;

import java.util.*;
//Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
//
//
//
//Example 1:
//
//Input: nums = [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
//Example 2:
//
//Input: nums = [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//Example 3:
//
//Input: nums = [0,1,1,1,1,1,0,0,0]
//Output: 6
//Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
//












/*
Why: If prev is not null, it means we have already encountered this exact running sum before.

Mathematical Rule:

Suppose the running sum was 5 at index prev.

After processing some more elements, the running sum is again 5 at index i.

Since the running sum did not change between index prev and index i,
the elements in between must have contributed a net sum of 0.

In this problem:

1 is treated as +1
0 is treated as -1

Therefore, a net sum of 0 means there are an equal number of +1s and -1s
between prev + 1 and i.

Hence, the subarray from (prev + 1) to i contains an equal number of 0s and 1s.
*/
public class ContiguousArray {
    public int findMaxLengthOptimal(int[] nums) {
        int n = nums.length;
        int[] prefixsum = new int[2*n+1];
        for(int i =0; i < prefixsum.length; i++){
            prefixsum[i] = -2;
        }
        prefixsum[n] = -1;
        int maxlen = 0;
        int count = n;
        for(int i =0; i < nums.length; i++){
            count += 2*nums[i]-1;
            if(prefixsum[count] == -2){
                prefixsum[count] = i;
            }

            else{
                maxlen = Math.max(maxlen , i-prefixsum[count]);
            }
        }
        return maxlen;
    }
    public int findMaxLengthO2(int[] nums) {
        int n = nums.length;
        int[] firstIndex = new int[2 * n + 1];
        Arrays.fill(firstIndex, -2);
        int offset = n;
        int count = 0;
        int maxLen = 0;
        firstIndex[offset] = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count--;
            } else {
                count++;
            }
            int index = count + offset;
            if (firstIndex[index] != -2) {
                maxLen = Math.max(maxLen, i - firstIndex[index]);
            } else {
                firstIndex[index] = i;
            }
        }
        return maxLen;
    }
    //logic: The idea is to use a HashMap to store the first occurrence of each cumulative sum. We iterate through the array, updating the cumulative sum by adding 1 for each 1 and subtracting 1 for each 0. If we encounter a cumulative sum that we've seen before, it means that the subarray between the previous index and the current index has an equal number of 0's and 1's. We calculate the length of this subarray and update the maximum length found so far.
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++) {
            if(nums[i] == 0) nums[i] = -1; //to balance the number of 0's and 1's, we can replace 0's with -1's. This way, if the sum of a subarray is 0, it means that there are equal number of 0's and 1's in that subarray.
        }
        int maxLen = 0;
        Map<Integer, Integer> hm = new HashMap<>();
        int sum = 0;
//if (prev != null)Why: If prev is not null, it means we did encounter this exact sum before!The Mathematical Rule: If your running sum was 5 at index prev, and after adding a bunch of elements it is still 5 at index i, it means the elements added in between added a net total of exactly 0 to our sum. If they added 0, there must be an identical number of $+1$s and $-1$s inside that window!
        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(sum == 0) maxLen = i+1;

            Integer prev = hm.get(sum);
            if(prev != null) maxLen = Math.max(maxLen, i - prev);

            else hm.put(sum, i);
        }

        return maxLen;
    }
    public static void main(String[] args) {
        ContiguousArray solution = new ContiguousArray();
        int[] nums = {0,1,0,1,0};
        int result = solution.findMaxLength(nums);
        System.out.println("Maximum length of contiguous array with equal number of 0's and 1's: " + result); // Output: 4
    }
}
