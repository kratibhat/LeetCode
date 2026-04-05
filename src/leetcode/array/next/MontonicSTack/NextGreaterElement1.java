package leetcode.array.next.MontonicSTack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
//
//You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
//
//For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
//
//Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
//
//
//
//Example 1:
//
//Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
//Output: [-1,3,-1]
//Explanation: The next greater element for each value of nums1 is as follows:
//- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
//- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//Example 2:
//
//Input: nums1 = [2,4], nums2 = [1,2,3,4]
//Output: [3,-1]
//Explanation: The next greater element for each value of nums1 is as follows:
//- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
//- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
public class NextGreaterElement1{
    private static final int[] next = new int[10001], stack = new int[1001];
    /// /0ms
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int top = -1;
        for(int num : nums2) {
            next[num] = -1;
            while(top != -1 && num > stack[top])
                next[stack[top--]] = num;
            stack[++top] = num;
        }
        for(int i = 0; i < nums1.length; i++) nums1[i] = next[nums1[i]];
        return nums1;
    }
    public static void main(String[] args) {
        NextGreaterElement1 solution = new NextGreaterElement1();

        int [] nums1 = {4,1,2};
        int [] nums2 = {1,3, 4,2};
        int[] result = solution.nextGreaterElement(nums1,nums2);
        System.out.print("Next Greater Elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return result;

    }
}
