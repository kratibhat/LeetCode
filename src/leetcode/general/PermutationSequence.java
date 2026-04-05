package leetcode.general;

import java.util.ArrayList;
import java.util.List;

//The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
//
//By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
//
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//Given n and k, return the kth permutation sequence.
//
//
//
//Example 1:
//
//Input: n = 3, k = 3
//Output: "213"
//Example 2:
//
//Input: n = 4, k = 9
//Output: "2314"
//Example 3:
//
//Input: n = 3, k = 1
//Output: "123"
//
//
//Constraints:
//
//1 <= n <= 9
//1 <= k <= n!
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder result = new StringBuilder();

        // Create a list of numbers to get indices from
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Precompute the factorials
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Adjust k to be zero-based index
        k--;

        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            result.append(numbers.get(index));
            numbers.remove(index);
            k -= index * factorial[n - i];
        }

        return result.toString();
    }

    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        int n = 4;
        int k = 9;
        String permutation = solution.getPermutation(n, k);
        System.out.println("The " + k + "th permutation of numbers from 1 to " + n + " is: " + permutation);
    }
}
