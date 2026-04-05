package leetcode.STRING.EASY.parenthesis;
//A parentheses string is valid if and only if:
//
//It is the empty string,
//It can be written as AB (A concatenated with B), where A and B are valid strings, or
//It can be written as (A), where A is a valid string.
//You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
//
//For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
//Return the minimum number of moves required to make s valid.
//
//
//
//Example 1:
//
//Input: s = "())"
//Output: 1
public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int open = 0; // Count of unmatched opening parentheses
        int close = 0; // Count of unmatched closing parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++; // Increment count for an opening parenthesis
            } else if (c == ')') {
                if (open > 0) {
                    open--; // Match with an unmatched opening parenthesis
                } else {
                    close++; // Increment count for an unmatched closing parenthesis
                }
            }
        }

        // Total unmatched parentheses is the sum of unmatched opening and closing
        return open + close;
    }
    public static void main(String[] args) {
        MinimumAddToMakeParenthesesValid solution = new MinimumAddToMakeParenthesesValid();
        String s = "())";
        int result = solution.minAddToMakeValid(s);
        System.out.println("Minimum additions to make valid: " + result); // Output: 1

    }
}
