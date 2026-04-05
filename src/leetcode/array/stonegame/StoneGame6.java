package leetcode.array.stonegame;

import java.util.Arrays;
import java.util.*;

//Alice and Bob take turns playing a game, with Alice starting first.
//
//There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.
//
//You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
//
//The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally. Both players know the other's values.
//
//Determine the result of the game, and:
//
//If Alice wins, return 1.
//If Bob wins, return -1.
//If the game results in a draw, return 0.
//
//
//Example 1:
//
//Input: aliceValues = [1,3], bobValues = [2,1]
//Output: 1
//Explanation:
//If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
//Bob can only choose stone 0, and will only receive 2 points.
//Alice wins.
//Example 2:
//
//Input: aliceValues = [1,2], bobValues = [3,1]
//Output: 0
//Explanation:
//If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
//Draw.
//Example 3:
//
//Input: aliceValues = [2,4,3], bobValues = [1,6,7]
//Output: -1
//Explanation:
//Regardless of how Alice plays, Bob will be able to have more points than Alice.
//For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
//Bob wins.
//
public class StoneGame6 {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        /// /16ms
        int n = aliceValues.length;

        // Since values ≤ 100, combined ≤ 200
        // Create fixed-size buckets
        List<Integer>[] buckets = new ArrayList[201]; // 0-200

        for (int i = 0; i <= 200; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Bucket sort: O(n)
        for (int i = 0; i < n; i++) {
            int combined = aliceValues[i] + bobValues[i];
            buckets[combined].add(i);
        }

        // Process in descending order: O(201 + n) = O(n)
        int aliceScore = 0, bobScore = 0;
        boolean aliceTurn = true;

        for (int val = 200; val >= 0; val--) {
            for (int idx : buckets[val]) {
                if (aliceTurn) {
                    aliceScore += aliceValues[idx];
                } else {
                    bobScore += bobValues[idx];
                }
                aliceTurn = !aliceTurn;
            }
        }

        if (aliceScore > bobScore) return 1;
        if (aliceScore < bobScore) return -1;
        return 0;
    }
    public static void main(String[] args) {
        StoneGame6 solution = new StoneGame6();
        int[] aliceValues = {1, 3};
        int[] bobValues = {2, 1};
        int result = solution.stoneGameVI(aliceValues, bobValues);
        System.out.println("Game Result: " + result); // Output: 1
    }
}
