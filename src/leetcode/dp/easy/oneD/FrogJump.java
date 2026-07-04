package leetcode.dp.easy.oneD;
//A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
//
//Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
//
//If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.
//
//
//
//Example 1:
//
//Input: stones = [0,1,3,5,6,8,12,17]
//Output: true
//Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
//Example 2:
//
//Input: stones = [0,1,2,3,4,8,9,11]
//Output: false
//Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
//
public class FrogJump {
    int[] stones;
    boolean[][] visited;
    public boolean canCross(int[] stones) {
        if (stones[1] != 1 || stones[stones.length - 1] > 2001000)
            return false;
        this.stones = stones;
        visited = new boolean[stones.length][stones.length];
        return canCross(1, 1);
    }
    public boolean canCross(int stone, int lastJump) {
        if (stone >= stones.length - 1) return true;

        if (visited[stone][lastJump]) return false;
        visited[stone][lastJump] = true;

        int nextStone = stone + 1;
        //The key idea: the loop doesn't find the next stone to jump to—it finds the first stone that is not impossible. After that, the code only needs to check at most three positions (k-1, k, and k+1 landing distances).
        while (nextStone < stones.length && stones[nextStone] < stones[stone] + lastJump - 1)
            nextStone++;
        //
        if (nextStone < stones.length && stones[nextStone] == stones[stone] + lastJump - 1
                && canCross(nextStone++, lastJump - 1))
            return true;
        if (nextStone < stones.length && stones[nextStone] == stones[stone] + lastJump
                && canCross(nextStone++, lastJump))
            return true;
        return nextStone < stones.length && stones[nextStone] == stones[stone] + lastJump + 1
                && canCross(nextStone, lastJump + 1);
    }
    public static void main(String[] args) {
        FrogJump solution = new FrogJump();
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        boolean result = solution.canCross(stones);
        System.out.println("Can the frog cross the river? " + result); // Output: true
    }
}
////Given stones
/// stones = [0, 1, 3, 5, 6, 8, 12, 17]
///
/// The stone positions are:
///
/// Position: 0   1   3   5   6   8   12   17
///           ●---●---●---●---●---●----●----●
///
/// The frog starts at position 0.
///
/// Rule
/// The first jump must be exactly 1 unit.
/// If the previous jump was k, then the next jump can be:
/// k - 1
/// k
/// k + 1
///
/// The frog can only land on stones.
///
/// Step 1
///
/// Current stone: 0
///
/// First jump must be 1.
///
/// 0 --(1)--> 1
///
/// Now:
///
/// Current position = 1
/// Last jump = 1
/// Step 2
///
/// Last jump = 1
///
/// Next jump can be:
///
/// 0
/// 1
/// 2
///
/// Possible destinations from position 1:
///
/// 1 + 0 = 1 (not useful)
/// 1 + 1 = 2 (no stone)
/// 1 + 2 = 3 ✅
///
/// So jump:
///
/// 1 --(2)--> 3
///
/// Now:
///
/// Current position = 3
/// Last jump = 2
/// Step 3
///
/// Last jump = 2
///
/// Next jump can be:
///
/// 1
/// 2
/// 3
///
/// From position 3:
///
/// 3 + 1 = 4 (no stone)
/// 3 + 2 = 5 ✅
/// 3 + 3 = 6 ✅
///
/// The example chooses 5.
///
/// 3 --(2)--> 5
///
/// Now:
///
/// Current position = 5
/// Last jump = 2
/// Step 4
///
/// Last jump = 2
///
/// Next jump can be:
///
/// 1
/// 2
/// 3
///
/// From position 5:
///
/// 5 + 1 = 6 ✅
/// 5 + 2 = 7 (no stone)
/// 5 + 3 = 8 ✅
///
/// The example chooses 8 directly (skipping 6).
///
/// 5 --(3)--> 8
///
/// Now:
///
/// Current position = 8
/// Last jump = 3
///
/// (The explanation also mentions stone 6 because it lists the sequence of jump sizes, but the successful path is effectively 0 → 1 → 3 → 5 → 8 → 12 → 17.)
///
/// Step 5
///
/// Last jump = 3
///
/// Next jump can be:
///
/// 2
/// 3
/// 4
///
/// From position 8:
///
/// 8 + 2 = 10 (no stone)
/// 8 + 3 = 11 (no stone)
/// 8 + 4 = 12 ✅
/// 8 --(4)--> 12
///
/// Now:
///
/// Current position = 12
/// Last jump = 4
/// Step 6
///
/// Last jump = 4
///
/// Next jump can be:
///
/// 3
/// 4
/// 5
///
/// From position 12:
///
/// 12 + 3 = 15 (no stone)
/// 12 + 4 = 16 (no stone)
/// 12 + 5 = 17 ✅
/// 12 --(5)--> 17
///
/// Reached the last stone!
///
/// Complete path
/// 0 --1--> 1 --2--> 3 --2--> 5 --3--> 8 --4--> 12 --5--> 17


/*
===========================================
FROG JUMP (DFS + MEMOIZATION) EXPLANATION
===========================================

Idea:
------
From every stone, the frog has at most 3 possible jumps:
    1. lastJump - 1
    2. lastJump
    3. lastJump + 1

We recursively try all three possibilities.
If any path reaches the last stone, return true.

To avoid solving the same state repeatedly, we use memoization.

----------------------------------------------------------
Class Variables
----------------------------------------------------------

int[] stones;
    -> Stores the input array as a class variable.
    -> This allows every recursive function to access the array.
    -> Otherwise, we would have to pass the stones array in every recursive call.

boolean[][] visited;
    -> Memoization table.

    visited[i][k] means:
    "Have we already explored the state where
     we are on stone index i and the previous jump was k?"

    If yes, we immediately return false because we've already
    determined that this state cannot reach the end.

----------------------------------------------------------
Main Function
----------------------------------------------------------

public boolean canCross(int[] stones)

This is the function called by LeetCode.

----------------------------------------------------------
if (stones[1] != 1 || stones[stones.length - 1] > 2001000)
    return false;

stones[1] != 1
----------------
The problem states that the FIRST jump MUST be exactly 1.

Example:

stones = [0,2,3,5]

The frog starts at 0.

First jump = 1

But there is no stone at position 1.

So crossing is impossible.

Return false immediately.

----------------------------------------------------------
stones[stones.length - 1] > 2001000

This is simply an optimization added by the author.

It is NOT required by the problem.

----------------------------------------------------------
this.stones = stones;

Stores the input array into the class variable.

Now every recursive function can directly access

stones[i]

instead of passing the array every time.

----------------------------------------------------------
visited = new boolean[stones.length][stones.length];

Creates the memoization table.

Initially every value is false.

Example:

        Jump Size
       0 1 2 3 4 ...

Stone0 F F F F
Stone1 F F F F
Stone2 F F F F

Meaning:
We have not explored any state yet.

----------------------------------------------------------
return canCross(1,1);

The frog has already made the mandatory first jump.

Current Stone Index = 1
Last Jump = 1

So recursion starts from there.

==========================================================
Recursive Function
==========================================================

public boolean canCross(int stone, int lastJump)

Parameters:

stone
------
Current STONE INDEX (not position).

Example:

stones = [0,1,3,5]

Index:

0 1 2 3

If stone = 2

Current position = stones[2] = 3

----------------------------------------------------------
lastJump

Represents the jump size used to reach the current stone.

==========================================================
Base Case
==========================================================

if (stone >= stones.length - 1)
    return true;

If we are already on the last stone,
the frog has successfully crossed the river.

Return true.

==========================================================
Memoization Check
==========================================================

if (visited[stone][lastJump])
    return false;

Suppose we have already explored:

Stone Index = 5
Last Jump = 3

and previously discovered that no path reaches the end.

If recursion reaches this same state again,

there is no reason to explore it again.

Return false immediately.

----------------------------------------------------------
visited[stone][lastJump] = true;

Marks this state as explored.

Next time recursion reaches the same state,
it will immediately return false.

==========================================================
Searching for Next Possible Stones
==========================================================

int nextStone = stone + 1;

The frog can only move forward.

So begin checking from the next stone.

==========================================================
while (nextStone < stones.length &&
       stones[nextStone] < stones[stone] + lastJump - 1)
    nextStone++;

Possible jump lengths are:

lastJump - 1
lastJump
lastJump + 1

The smallest possible landing position is:

stones[stone] + lastJump - 1

If a stone lies before this position,
it is impossible to land there.

So skip all such stones.

Example:

Current Position = 10
Last Jump = 5

Possible jumps:

4
5
6

Minimum landing position = 14

Stones:

10 11 12 13 14 16

11,12,13 are impossible.

Skip directly to 14.

This is an optimization.

==========================================================
Try Jump = lastJump - 1
==========================================================

if (nextStone < stones.length &&
    stones[nextStone] == stones[stone] + lastJump - 1 &&
    canCross(nextStone++, lastJump - 1))
    return true;

Meaning:

1. Check array boundary.

2. Check whether a stone exists exactly
   lastJump-1 units away.

3. If yes,
   recursively check whether crossing is possible
   from that stone.

If recursion returns true,

immediately return true.

----------------------------------------------------------
Why nextStone++ ?

Suppose

nextStone = 5

canCross(nextStone++, 4)

passes value 5 into recursion.

AFTER the function call,

nextStone becomes 6.

Reason:

After checking jump = lastJump-1,

the programmer wants nextStone to point to the next
candidate while checking jump = lastJump.

Equivalent code would be:

canCross(nextStone, lastJump-1);
nextStone++;

==========================================================
Try Jump = lastJump
==========================================================

if (nextStone < stones.length &&
    stones[nextStone] == stones[stone] + lastJump &&
    canCross(nextStone++, lastJump))
    return true;

Exactly the same idea.

Checks whether there is a stone exactly

lastJump

units away.

If recursion succeeds,

return true.

Again,

nextStone++ moves to the next candidate
for checking jump = lastJump + 1.

==========================================================
Try Jump = lastJump + 1
==========================================================

return nextStone < stones.length &&
       stones[nextStone] == stones[stone] + lastJump + 1 &&
       canCross(nextStone, lastJump + 1);

Checks the final possible jump.

No nextStone++ is required here because
this is the final check.

If recursion succeeds,

return true.

Otherwise,

return false.

==========================================================
Overall Algorithm
==========================================================

Current Stone
      |
      |
Possible Jumps
      |
-------------------------
|         |             |
k-1       k           k+1
|         |             |
DFS      DFS          DFS
|         |             |
If any path reaches the last stone
return true.

Otherwise
return false.

==========================================================
Memoization State
==========================================================

A recursive state is uniquely identified by:

(Current Stone Index, Last Jump Size)

Example:

(index = 5, jump = 3)

If this exact state has already been explored,

do NOT solve it again.

This prevents exponential repeated work.

==========================================================
Time Complexity

Approximately O(n²)

Reason:
Each state (stoneIndex, jumpSize) is visited at most once.

==========================================================
Space Complexity

O(n²)

visited[][] stores one value for every
(stone index, jump size) pair.
==========================================================
*/


////Example 3
///
/// Suppose
///
/// Current position = 20
///
/// lastJump = 6
///
/// Possible jumps
///
/// 5
/// 6
/// 7
///
/// Minimum landing position
///
/// 20 + 5 = 25
///
/// Remaining stones
///
/// 21
/// 22
/// 24
/// 25
/// 26
/// 27
///
/// The loop works like this:
///
/// 21 < 25 → Skip
///
/// 22 < 25 → Skip
///
/// 24 < 25 → Skip
///
/// 25 < 25 → Stop
///
/// Now nextStone is directly at 25, the first stone that could possibly be reached.
///
/// Why is this optimization useful?
///
/// Imagine there are many stones:
///
/// 20 21 22 23 24 25 26 27 28
///
/// Without the while loop, you'd check:
///
/// Can I jump to 21?
///
/// Can I jump to 22?
///
/// Can I jump to 23?
///
/// Can I jump to 24?
///
/// But all of these are impossible because the smallest jump is 5.
///
/// The loop skips all impossible stones in one go and starts checking only from the first position that could be reached.
///
/// Visual summary
///
/// Suppose:
///
/// Current Position = 8
/// lastJump = 4
///                 Minimum possible landing = 8 + (4 - 1) = 11
///
/// 8 ---- 9 ---- 10 ---- 11 ---- 12 ---- 13
/// ^      X      X       ✓       ✓       ✓
/// |
/// Current stone
///
/// X = Impossible (too close, skipped by while loop)
/// ✓ = Worth checking