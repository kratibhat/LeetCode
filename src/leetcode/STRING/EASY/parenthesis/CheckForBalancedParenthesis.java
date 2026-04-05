package leetcode.STRING.EASY.parenthesis;
//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//An input string is valid if:
//
//Open brackets must be closed by the same type of brackets.
//Open brackets must be closed in the correct order.
//Every close bracket has a corresponding open bracket of the same type.
//Input: s = "()"
//
//Output: true
public class CheckForBalancedParenthesis {
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
    public static void main(String[] args) {
        CheckForBalancedParenthesis solution = new CheckForBalancedParenthesis();
        String s = "({[]})";
        boolean result = solution.isValid(s);
        System.out.println("Is the string \"" + s + "\" valid? " + result); // Output: true
    }

}
