package leetcode.STRING.EASY;
//Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
//
//The algorithm for myAtoi(string s) is as follows:
//
//Whitespace: Ignore any leading whitespace (" ").
//Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
//Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
//Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
//Return the integer as the final result.
//
//
//
//Example 1:
//
//Input: s = "42"
//
//Output: 42
//
//Explanation:
//
//The underlined characters are what is read in and the caret is the current reader position.
//Step 1: "42" (no characters read because there is no leading whitespace)
//         ^
//Step 2: "42" (no characters read because there is neither a '-' nor '+')
//         ^
//Step 3: "42" ("42" is read in)
public class ImplementAtoi {
    public int myAtoi(String s) {
        int i = 0, sign = 1, result = 0;
        // Discard whitespaces in the beginning
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        // Check for sign
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        // Convert number and avoid overflow
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // Check for overflow
            //if "2147483648" this is the result thn overflow will happen for - sign
            //result * 10 + digit ≤ Integer.MAX_VALUE

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }
        return result * sign;
    }
    public static void main(String[] args) {
        ImplementAtoi solution = new ImplementAtoi();
        String s = "   -42";
        int result = solution.myAtoi(s);
        System.out.println("The converted integer is: " + result);
    }
}
