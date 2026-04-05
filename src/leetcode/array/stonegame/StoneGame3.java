package leetcode.array.stonegame;
//Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
//
//Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.
//
//The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.
//
//The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
//
//Assume Alice and Bob play optimally.
//
//Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.
//
//
//
//Example 1:
//
//Input: stoneValue = [1,2,3,7]
//Output: "Bob"
//Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
//Example 2:
//
//Input: stoneValue = [1,2,3,-9]
//Output: "Alice"
//Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
//If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
//If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
//Remember that both play optimally so here Alice will choose the scenario that makes her win.
//Example 3:
//
//Input: stoneValue = [1,2,3,6]
//Output: "Tie"
//Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
public class StoneGame3 {
    public String stoneGameIIIOptimal(int[] stoneValue) {
        /// //3ms
        int n = stoneValue.length;

        int one = 0;
        int two = 0;
        int three = 0;
        //Just like in Stone Game II, this is a bottom-up approach. We need to know the outcome of the end of the game to decide the best move at the start.
        //
        //When i = n-1 (the last stone), there are no stones left for the next player, so the values of one, two, and three start at 0.
        //
        //As the loop moves toward i = 0, it builds up the "best possible lead" Alice can have from the very beginning.
        for(int i=n-1; i >= 0; i--) {
            int cur = stoneValue[i] - one;
            if (i+1 < n) cur = Math.max(cur, stoneValue[i] + stoneValue[i+1] - two);
            if (i+2 < n) cur = Math.max(cur, stoneValue[i] + stoneValue[i+1] + stoneValue[i+2] - three);
//What happens if I leave the opponent at i+1? (Stored in one)
//
//What happens if I leave the opponent at i+2? (Stored in two)
//
//What happens if I leave the opponent at i+3? (Stored in three)
            three = two;
            two = one;
            one = cur;
        }

        if (one > 0) return "Alice";
        else if (one < 0) return "Bob";
        return "Tie";
    }
    public String stoneGameIII(int[] stoneValue) {
        /// 10ms
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0;

            for (int j = i; j < Math.min(n, i + 3); j++) {
                take += stoneValue[j];
                dp[i] = Math.max(dp[i], take - dp[j + 1]);
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
    public static void main(String[] args) {
        StoneGame3 solution = new StoneGame3();
        int[] stoneValue1 = {1, 2, 3, 7};
        System.out.println(solution.stoneGameIIIOptimal(stoneValue1)); // Output: "Bob"

        int[] stoneValue2 = {1, 2, 3, -9};
        System.out.println(solution.stoneGameIIIOptimal(stoneValue2)); // Output: "Alice"

        int[] stoneValue3 = {1, 2, 3, 6};
        System.out.println(solution.stoneGameIIIOptimal(stoneValue3)); // Output: "Tie"
    }
}
//+---------------------------+----------------------------------------------------------------------------------------------------------------------------------+
//| Aspect                    | Explanation                                                                                                                      |
//+---------------------------+----------------------------------------------------------------------------------------------------------------------------------+
//| Core Concept              | Uses Relative Score (current player’s score − opponent’s score) instead of absolute scores.                                     |
//| Winning Condition         | score > 0 → Alice wins                                                                                                            |
//|                           | score < 0 → Bob wins                                                                                                              |
//|                           | score = 0 → Tie                                                                                                                   |
//| DP State Meaning          | dp[i] represents the maximum relative score the current player can achieve starting from index i.                               |
//| Transition Formula        | dp[i] = max(stones taken − dp[next index])                                                                                        |
//| Why Subtraction?          | Opponent plays optimally; subtracting their best result gives the net advantage for the current player.                          |
//| Allowed Moves             | A player can take 1, 2, or 3 stones in a single turn.                                                                             |
//| Decision Options          | Take 1 → stoneValue[i] − dp[i+1]                                                                                                  |
//|                           | Take 2 → stoneValue[i] + stoneValue[i+1] − dp[i+2]                                                                                |
//|                           | Take 3 → stoneValue[i] + stoneValue[i+1] + stoneValue[i+2] − dp[i+3]                                                              |
//| Best Choice               | Choose the maximum of the three options using Math.max.                                                                           |
//| Space Optimization        | Only dp[i+1], dp[i+2], dp[i+3] are required → no full DP array needed.                                                            |
//| Variable Mapping          | one   → dp[i+1]                                                                                                                   |
//|                           | two   → dp[i+2]                                                                                                                   |
//|                           | three → dp[i+3]                                                                                                                   |
//| Update Rule               | After computing cur: three = two; two = one; one = cur;                                                                          |
//| Loop Direction            | Iterate backwards from n−1 to 0 (bottom-up DP).                                                                                   |
//| Base Case                 | At the end of the array, no stones remain → one = two = three = 0.                                                                |
//| Final Result              | one holds dp[0], the relative score starting from the beginning of the game.                                                      |
//| Time Complexity           | O(n)                                                                                                                              |
//| Space Complexity          | O(1)                                                                                                                              |
//+---------------------------+----------------------------------------------------------------------------------------------------------------------------------+