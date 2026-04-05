package leetcode.array.stonegame;
//Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.
//
//Alice and Bob take turns, with Alice starting first.
//
//On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.
//
//The game continues until all the stones have been taken.
//
//Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
//
// Example 1:
//
//Input: piles = [2,7,9,4,4]
//
//Output: 10
//
//Explanation:
//
//If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
//If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
//So we return 10 since it's larger.
//
//Example 2:
//Input: piles = [1,2,3,4,5,100]
//
//Output: 104
public class StoneGame2 {
    private int getMaxStones(int[] piles,int[] suffixSum,int[][] dp,int i,int M)
    {
        int n=piles.length;
        if(i==n)
        {
            return 0;
        }
        if(2*M>=n-i)
        {
            return suffixSum[i];
        }
        if(dp[i][M]>0)
        {
            return dp[i][M];
        }
        int minStonesForBob=Integer.MAX_VALUE;
        for(int X=1;X<=2*M;X++)
        {
            minStonesForBob=Math.min(minStonesForBob,getMaxStones(piles,suffixSum,dp,i+X,Math.max(M,X)));
        }
        //
        dp[i][M]=suffixSum[i]-minStonesForBob;
        return dp[i][M];
    }
    public int stoneGameII(int[] piles) {
        int n=piles.length;
        if(n==1)
        {
            return piles[0];
        }
        int[] suffixSum=new int[n];
        suffixSum[n-1]=piles[n-1];
        for(int i=n-2;i>=0;i--)
        {
            suffixSum[i]=suffixSum[i+1]+piles[i];
        }
        //(The index can't exceed the array length, and the multiplier can't realistically grow larger than the number of items left).
        int[][] dp=new int[n][n+1];
        return getMaxStones(piles,suffixSum,dp,0,1);

    }
    public static void main(String[] args) {
        int[] piles = {2, 7, 9, 4, 4};
        StoneGame2 solution = new StoneGame2();
        int result = solution.stoneGameII(piles);
        System.out.println("Maximum stones Alice can get: " + result); // Output: 10

    }
}
/*
Remaining list: [1, 2, 3, 4, 5, 100]

The real optimal path showing how Alice secures 104:

Turn 1: Alice's move
Alice sees the 100 at the end and wants to ensure she reaches it.
M = 1, so Alice can take X = 1 or X = 2 piles.
Alice takes 1 pile (value 1).

Score:
Alice = 1, Bob = 0
Remaining piles = [2, 3, 4, 5, 100]
New M = max(1, 1) = 1

Turn 2: Bob's move
M = 1, so Bob can take 1 or 2 piles.
If Bob takes 2 piles (2, 3), M becomes 2 and Alice could then
take up to 4 piles and grab the 100.
To prevent this, Bob takes only 1 pile (value 2).

Score:
Alice = 1, Bob = 2
Remaining piles = [3, 4, 5, 100]
New M = 1

Turn 3: Alice's move
M = 1, so Alice takes 1 pile (value 3).

Score:
Alice = 4, Bob = 2
Remaining piles = [4, 5, 100]
New M = 1

Turn 4: Bob's move
M = 1, Bob can take 1 or 2 piles.
If Bob takes 1 pile (4), Alice can take (5, 100) next.
If Bob takes 2 piles (4, 5), M becomes 2 and Alice takes 100.
Bob chooses (4, 5) to maximize his own score.

Score:
Alice = 4, Bob = 11
Remaining piles = [100]

Turn 5: Alice's move
Alice takes the final pile (100).

Final score:
Alice = 104
Bob = 11
*/
/*
STONE GAME II – CORE IDEA EXPLAINED
----------------------------------

1) STRATEGY: SUFFIX SUM
----------------------
suffixSum[i] represents the total stones available
from index i to the end of the piles array.

Why do we use it?
If it is Alice’s turn at index i, and Bob will get
some stones from the remaining piles, then:

AliceScore = suffixSum[i] - BobScore

So once we know how many stones Bob can force,
Alice automatically gets the rest.
*/
/*
2) DYNAMIC RULES: M AND X
------------------------
- On each turn, a player can take X piles
  where 1 <= X <= 2 * M

- After taking X piles, the next player’s
  M becomes max(M, X)

This means:
Taking more piles now increases how many piles
the opponent can take later.
*/
/*
3) RECURSIVE FUNCTION: getMaxStones(i, M)
----------------------------------------
This function returns the maximum stones
the current player can collect starting
from index i with parameter M.
*/
/*
A) GREEDY BASE CASE
------------------
If the current player can take all remaining piles
in one move, they should do it immediately.
*/
// if (2 * M >= n - i) {
//     return suffixSum[i];
// }
/*/*
Why this works:
- n - i is the number of piles left
- If 2 * M >= remaining piles,
  the player can grab everything
- This is clearly the optimal move
*/
/*
B) MINIMAX LOGIC (MINIMIZING THE OPPONENT)
-----------------------------------------
The current player tries every possible X
(from 1 to 2 * M).

For each X:
- Control passes to the opponent
- We compute how many stones the opponent
  can get from the next position

The current player wants to minimize
the opponent’s maximum gain.
*/
//int minStonesForBob = Integer.MAX_VALUE;
//
//for (int X = 1; X <= 2 * M; X++) {
//minStonesForBob = Math.min(
//        minStonesForBob,
//        getMaxStones(
//                i + X,
//        Math.max(M, X)
//        )
//                );
                //}
                /*
Key idea:
- Bob plays optimally on his turn
- Alice assumes Bob will get the maximum
  possible stones
- So Alice chooses the move that results
  in the minimum possible stones for Bob
*/
/*
C) FINAL RESULT CALCULATION
---------------------------
Once we know the minimum stones Bob can get,
Alice gets everything else from the suffix.
*/
//dp[i][M] = suffixSum[i] - minStonesForBob;

///*
//Final intuition:
//- suffixSum[i] = total stones left
//- Bob gets minStonesForBob (worst case for Alice)
//- Alice gets the rest
//
//This converts the game into:
//"Total remaining stones minus what the opponent
//can optimally secure."
//*/
