package leetcode.backTracking;

import java.util.ArrayList;
import java.util.List;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//
//
//Example 1:
//
//Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
//Example 2:
//
//Input: n = 1
//Output: ["()"]
public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis solution = new GenerateParenthesis();
        int n = 3;
        List<String> parentheses = solution.generateParenthesis(n);
        System.out.println("Generated Parentheses: " + parentheses);
    }
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        //➡️ Number of ) used so far must be LESS than number of ( used so far
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }


    //Base condition (STOP condition)
    //if (current.length() == max * 2) {
    //    result.add(current);
    //    return;
    //}
    //
    //
    //Each valid string must have max '(' and max ')'
    //
    //Total length = 2 * max
    //
    //Once reached → this is a complete valid combination
    //
    //Add it to result and stop this path
    //
    //Rule 1: Add an opening bracket '('
    //if (open < max) {
    //    backtrack(result, current + "(", open + 1, close, max);
    //}
    //
    //Why this works:
    //
    //You can add '(' only if you haven't used all max opening brackets
    //
    //Prevents more than max '('
    //
    //Example:
    //max = 3
    //current = "(("
    //open = 2
    //→ allowed to add '(' → "((("
    //
    //Rule 2: Add a closing bracket ')'
    //if (close < open) {
    //    backtrack(result, current + ")", open, close + 1, max);
    //}
    //
    //Why this rule is CRUCIAL:
    //
    //You can only close a bracket if there is an open one to match
    //
    //This guarantees valid parentheses
    //
    //Prevents invalid strings like ")(", "())("
    //
    //Example:
    //current = "("
    //open = 1
    //close = 0
    //→ allowed to add ')'
    //
    //
    //But:
    //
    //current = ")"
    //open = 0
    //close = 1
    //→ NOT allowed (invalid)
    //
    //Why this guarantees ONLY valid combinations
    //Condition	Prevents
    //open < max	Too many '('
    //close < open	Closing before opening
    //length == max * 2	Incomplete strings
    //
    //👉 Invalid strings are never even created
    //
    //Visual flow example (max = 2)
    //start: ""
    // ├── "("
    // │    ├── "(("
    // │    │     └── "(())" ✔
    // │    └── "()"
    // │          └── "()()" ✔
    //
    //Why backtracking is perfect here
    //
    //Tries all possibilities
    //
    //Stops early when invalid
    //
    //Efficient compared to brute force
    //
    //In one sentence:
    //
    //This algorithm builds the string step-by-step, only adding parentheses when they keep the string valid, and saves it once it reaches the required length.
    //
    //If you want, I can also:
    //
    //Dry-run this with max = 3
    //
    //Convert it to iterative
    //
    //Explain how this is better than brute force 💡
}
