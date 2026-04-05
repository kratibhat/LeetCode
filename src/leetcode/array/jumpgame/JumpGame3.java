package leetcode.array.jumpgame;

import java.util.ArrayList;
import java.util.List;

//Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
//
//Notice that you can not jump outside of the array at any time.
//
//
//
//Example 1:
//
//Input: arr = [4,2,3,0,3,1,2], start = 5
//Output: true
//Explanation:
//All possible ways to reach at index 3 with value 0 are:
//index 5 -> index 4 -> index 1 -> index 3
//index 5 -> index 6 -> index 4 -> index 1 -> index 3
public class JumpGame3 {
    List<Integer> l = new ArrayList<Integer>();


    public static void main(String[] args) {
        JumpGame3 solution = new JumpGame3();
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        boolean result = solution.canReach1(arr, start);
        System.out.println("Can reach zero: " + result); // Output: true
    }
    public boolean canReach1(int[] arr, int start) {
        /// 2ms solution
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }
    public boolean dfs(int[] arr, int index, boolean[] visited) {
        if (index < 0 || index >= arr.length || visited[index]) {
            return false;
        }
        if (arr[index] == 0) {
            return true;
        }

        visited[index] = true;

        return dfs(arr, index + arr[index], visited) || dfs(arr, index - arr[index], visited);
    }
}
