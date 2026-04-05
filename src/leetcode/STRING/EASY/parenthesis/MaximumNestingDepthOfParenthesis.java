package leetcode.STRING.EASY.parenthesis;
//Given a valid parentheses string s,
// return the nesting depth of s.
// The nesting depth is the maximum number of nested parentheses.
//
//
//
//Example 1:
//
//Input: s = "(1+(2*3)+((8)/4))+1"
//
//Output: 3
//
//Explanation:
//
//Digit 8 is inside of 3 nested parentheses in the string.
public class MaximumNestingDepthOfParenthesis {
    public int maxDepth(String s) {
        int currentDepth = 0;
        int maxDepth = 0;
//SIMILAR TO BALANCED PARENTHESIS /REMOVE OUTERMOST PARENTHESIS
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }

        return maxDepth;
    }

}
