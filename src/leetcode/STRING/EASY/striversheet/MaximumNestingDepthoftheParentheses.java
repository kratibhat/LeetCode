package leetcode.STRING.EASY.striversheet;
/// /Given a valid parentheses string s, return the nesting depth of s.
/// The nesting depth is the maximum number of nested parentheses.
///
///
///
/// Example 1:
///
/// Input: s = "(1+(2*3)+((8)/4))+1"
///
/// Output: 3
///
/// Explanation:
///
/// Digit 8 is inside of 3 nested parentheses in the string.
///
/// Example 2:
///
/// Input: s = "(1)+((2))+(((3)))"
///
/// Output: 3
///
/// Explanation:
///
/// Digit 3 is inside of 3 nested parentheses in the string.
///
/// Example 3:
///
/// Input: s = "()(())((()()))"
///
/// Output: 3
public class MaximumNestingDepthoftheParentheses {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int currentDepth = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                currentDepth++;
                // Update the maximum depth reached so far
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (ch == ')') {
                currentDepth--;
            }
            // All other characters (like numbers, +, *, etc.) are ignored
        }

        return maxDepth;
    }
    public static void main(String []args){
        MaximumNestingDepthoftheParentheses m=new MaximumNestingDepthoftheParentheses();
        String s="(1+(2*3)+((8)/4))+1";
        System.out.println(m.maxDepth(s));
    }
}
