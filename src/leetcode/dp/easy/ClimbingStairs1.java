// java
package leetcode.dp.easy;
//Explanation: "Order doesn't matter" means only the counts of 2-steps and 1-steps matter (not their order). Let k = number of 2-steps. Then 0 ≤ k ≤ floor(n/2). For each k there is exactly one unordered way (ones = n - 2*k). So count = floor(n/2) + 1.
//Example n = 5:
//floor(5/2) = 2 → k ∈ {2,1,0} → 3 ways:
//k=2 → ones=1 → {2,2,1}
//k=1 → ones=3 → {2,1,1,1}
//k=0 → ones=5 → {1,1,1,1,1}
//Example n = 4:
//floor(4/2) = 2 → k ∈ {2,1,0} → 3 ways:
//k=2 → ones=0 → {2,2}
//k=1 → ones=2 → {2,1,1}
//k=0 → ones=4 → {1,1,1,1}
public class ClimbingStairs1 {

    // O(1) time, O(1) space: number of unordered ways = floor(n/2) + 1
    public static int nthStair(int n) {
        return n / 2 + 1;
    }

    // print unordered combinations as (count_of_2, count_of_1)
    public static void printUnorderedWays(int n) {
        int s = n / 2;
        System.out.println("n = " + n + ", ways = " + (s + 1));//final answer
        for (int k = s; k >= 0; k--) {
            int twos = k;
            int ones = n - 2 * k;
            System.out.println("  2-steps: " + twos + ", 1-steps: " + ones);
        }
    }

    public static void main(String[] args) {
        printUnorderedWays(5); // shows the 3 unordered ways for n=5
        printUnorderedWays(4); // shows the 3 unordered ways for n=4
    }
}
