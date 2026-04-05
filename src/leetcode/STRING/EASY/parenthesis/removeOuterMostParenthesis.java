package leetcode.STRING.EASY.parenthesis;
//A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
//
//For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
//A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
//
//Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
//
//Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
//Input: s = "(()())(())"
//Output: "()()()"
//Explanation:
//The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
//After removing outer parentheses of each part, this is "()()" + "()" = "()()()"
public class removeOuterMostParenthesis {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (openCount > 0) {
                    result.append(c);
                }
                openCount++;  //NOTE: Increment after checking openCount>)
            } else if (c == ')') {
                openCount--;
                if (openCount > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
    public static void main(String[] args) {
        removeOuterMostParenthesis solution = new removeOuterMostParenthesis();
        String s = "((())())";
        String result = solution.removeOuterParentheses(s);
        System.out.println("Result after removing outermost parentheses: " + result); // Output: "()()()"
    }


}
