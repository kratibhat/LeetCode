package leetcode.array.jumpgame;
/// /REFER ALGORITHMS MADE EASY VIDEO JUMP GAME 4
import java.util.*;

//Given an array of integers arr, you are initially positioned at the first index of the array.
//
//In one step you can jump from index i to index:
//
//i + 1 where: i + 1 < arr.length.
//i - 1 where: i - 1 >= 0.
//j where: arr[i] == arr[j] and i != j.
//Return the minimum number of steps to reach the last index of the array.
//
//Notice that you can not jump outside of the array at any time.
//
//
//
//Example 1:
//
//Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
//Output: 3
//Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
public class JumpGame4 {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return 0;

        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int j = q.poll();
                if (j - 1 >= 0 && valueToIndices.containsKey(arr[j - 1])) {

                    q.offer(j - 1);
                }
                if (j + 1 < n && valueToIndices.containsKey(arr[j + 1])) {
                    if (j + 1 == n - 1)
                        return steps;
                    q.offer(j + 1);
                }
                if (valueToIndices.containsKey(arr[j])) {
                    for (int idx : valueToIndices.get(arr[j])) {
                        if (idx != j) {
                            if (idx == n - 1)
                                return steps;
                            q.offer(idx);
                        }
                    }
                    valueToIndices.remove(arr[j]);
                }

            }
        }
        return steps;
    }
    public static void main(String[] args) {
        JumpGame4 solution = new JumpGame4();
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        int result = solution.minJumps(arr);
        System.out.println("Minimum number of jumps to reach the last index: " + result); // Output: 3
    }
}
