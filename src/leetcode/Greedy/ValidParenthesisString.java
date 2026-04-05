package leetcode.Greedy;
//Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
//
//The following rules define a valid string:
//
//Any left parenthesis '(' must have a corresponding right parenthesis ')'.
//Any right parenthesis ')' must have a corresponding left parenthesis '('.
//Left parenthesis '(' must go before the corresponding right parenthesis ')'.
//'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
//
//
//Example 1:
//
//Input: s = "()"
//Output: true
//Example 2:
//
//Input: s = "(*)"
//Output: true
//Example 3:
//
//Input: s = "(*))"
//Output: true
//
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int low = 0;  // Min possible open '('
        int high = 0; // Max possible open '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { // c == '*'
                low--;  // Treat as ')'
                high++; // Treat as '('
            }

            // If max possible open '(' is negative, we have too many ')'
            if (high < 0) return false;

            // low cannot be negative (we can't have "negative" open parentheses)
            // If low becomes negative, we reset it to 0 (treat some '*' as empty)
            if (low < 0) low = 0;
        }

        // If at the end, the minimum possible open '(' is 0, it's valid
        return low == 0;

    }

    public static void main(String[] args) {
        ValidParenthesisString solution = new ValidParenthesisString();
        String s1 = "()";
        String s2 = "(*)";
        String s3 = "(*))";
        System.out.println(solution.checkValidString(s1)); // Expected output: true
        System.out.println(solution.checkValidString(s2)); // Expected output: true
        System.out.println(solution.checkValidString(s3)); // Expected output: true
    }
}
//If (: Both low and high increase because another bracket is definitely open.
//
//If ): Both low and high decrease because a bracket is definitely closed.
//
//If *: This is where the range expands. high goes up (treating it as () and low goes down (treating it as )).
//
//The "Safety Net" (if (high < 0)): If even our most optimistic guess (high) is negative, it means we have encountered a ) that no amount of stars or brackets can fix. It's impossible. Return false.
//
//The "Reset" (if (low < 0) low = 0): We can't have "negative" open brackets. If low goes below zero, it just means we treated a * as a ) when we should have treated it as an empty string "". We reset it to zero to stay within a valid state.


//A. The "Multiple Identities" SignalWhenever a character in a string can represent multiple values (like * being (, ), or ""), you are dealing with a State Space problem.Initial thought: "I should use Recursion/Backtracking to try all 3 options."Optimization thought: "If I can track the range (min/max) of those options instead of branching, I can do it in $O(N)$."B. The "Balance" RequirementParenthesis problems are almost always about keeping a balance counter. When that counter can be a range rather than a single number, the Min-Max Greedy approach is the standard optimal solution.C. The "Invalid if High < 0" ConstraintThis is a classic property of prefix sums. In any valid parenthesis string, at no point can the number of closing brackets exceed the number of opening brackets. If your "max possible" opening brackets (high) is ever less than your current closing brackets, the string is dead.