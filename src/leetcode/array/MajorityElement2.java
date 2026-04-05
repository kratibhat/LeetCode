package leetcode.array;

import java.util.*;

//Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//
//
//
//Example 1:
//
//Input: nums = [3,2,3]
//Output: [3]
//Example 2:
//
//Input: nums = [1]
//Output: [1]
//Example 3:
//
//Input: nums = [1,2]
//Output: [1,2]
//
public class MajorityElement2 {
    public List<Integer> majorityElementOptimal(int[] nums) {
        return new AbstractList<>(){
            List<Integer> res = null;
            private void build(){
                if(res!=null) return;
                res = new ArrayList<>();
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for(int x: nums) map.put(x, map.getOrDefault(x, 0)+1);
                for(int x: map.keySet()){
                    if(map.get(x)>Math.floor(nums.length/3)) res.add(x);
                }
            }
            @Override
            public Integer get(int i){
                build();
                return res.get(i);
            }
            @Override
            public int size(){
                build();
                return res.size();
            }
        };

    }
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        int candidate1 = 0, candidate2 = 1; // Start with different values
        int count1 = 0, count2 = 0;

        // Step 1: Find potential candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Verify candidates
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);

        return result;
    }
    public static void main(String [] args) {
        MajorityElement2 solution = new MajorityElement2();
        int[] nums = {3, 2, 3};
        List<Integer> result = solution.majorityElementOptimal(nums);
        System.out.println("Majority elements: " + result);
    }
}
