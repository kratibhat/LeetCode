package leetcode.array.stonegame;
//Alice and Bob take turns playing a game, with Alice starting first.
//
//Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
//
//Also, if a player cannot make a move, he/she loses the game.
//
//Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
//
//
//
//Example 1:
//
//Input: n = 1
//Output: true
//Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
//Example 2:
//
//Input: n = 2
//Output: false
//Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
//Example 3:
//
//Input: n = 4
//Output: true
//Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
//
public class StoneGame4 {
    public boolean winnerSquareGameOptimal(int n) {
        /// /3ms
        boolean dp[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            if (dp[i])
                continue;
            for (int j = 1; i + j * j <= n; j++) {
                dp[i + j * j] = true;
            }
        }
        return dp[n];
    }
    public boolean winnerSquareGame(int n) {
        /// /14ms
        boolean[] dp = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
    public static void main(String[] args) {
        StoneGame4 solution = new StoneGame4();
        int n = 7;
        boolean result = solution.winnerSquareGameOptimal(n);
        System.out.println("Does Alice win the game with " + n + " stones? " + result); // Output: false
    }
}
//1. The Core Logic: Winning vs. Losing PositionsIn game theory, every position (number of stones)
// is either a Winning Position (True) or a Losing Position (False).Losing Position (False):
// If you are at a position where every possible move leads to a winning position for your opponent,
// you are in a losing spot.Winning Position (True): If there is at least one move you can make that
// puts your opponent in a losing position, you win.2. The "Push" StrategyMost DP solutions look
// backward (dp[i] = can I reach a false state from here?). This specific code looks forward:Initialize:
// boolean dp[] defaults all values to false.The Loop: We iterate through every number of stones i from 0 to n.
// If dp[i] is False: This means the person who starts at i stones is in a losing position.
// The Impact: If i is a losing position, then any number of stones that can jump to i must be a winning position.
// The Update: For every perfect square j*j, the state i + j*j is marked as true.Why skip dp[i] == true?
// If dp[i] is already true, it means the person starting there is in a winning position.
// Knowing this doesn't help us guarantee that a previous state was a losing one.
// We only "push" from losing states to mark all reachable future states as winning ones.
// 3. Step-by-Step Example: $n = 7$i = 0: dp[0] is false (Losing).Push to: $0+1=1$, $0+4=4$, $0+9=9$...dp[1], dp[4] become true.i = 1: dp[1] is true. Skip it (Continue).i = 2: dp[2] is false (Losing).Push to: $2+1=3$, $2+4=6$...dp[3], dp[6] become true.i = 3: dp[3] is true. Skip.i = 4: dp[4] is true. Skip.i = 5: dp[5] is false (Losing).Push to: $5+1=6$, $5+4=9$...dp[6] is already true. dp[9] becomes true.