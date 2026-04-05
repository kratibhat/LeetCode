package leetcode.STRING.EASY;
//Given an encoded string, return its decoded string.
//
//The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
//You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
//
//The test cases are generated so that the length of the output will never exceed 105.
//
//
//
//Example 1:
//
//Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
//Example 2:
//
//Input: s = "3[a2[c]]"
//Output: "accaccacc"
//Example 3:
public class DecodeString {

    int i = 0; // Global index to track progress across recursion levels

    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();

        while (i < s.length() && s.charAt(i) != ']') {
            if (Character.isDigit(s.charAt(i))) {
                int k = 0;
                while (Character.isDigit(s.charAt(i))) {
                    k = k * 10 + (s.charAt(i) - '0');
                    i++;
                }

                i++; // Skip '['
                String nested = decodeString(s); // Recursive call
                i++; // Skip ']'

                result.append(nested.repeat(k));
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
        String s1 = "3[a]2[bc]";
        System.out.println(solution.decodeString(s1)); // Output: "aaabcbc"

        String s2 = "3[a2[c]]";
        System.out.println(solution.decodeString(s2)); // Output: "accaccacc"

        String s3 = "2[abc]3[cd]ef";
        System.out.println(solution.decodeString(s3)); // Output: "abcabccdcdcdef"
    }

}
