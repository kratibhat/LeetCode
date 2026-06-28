package leetcode.STRING.EASY.striversheet;

public class LargestOddNumberinString {
    public static String largestOddNumber(String num) {
        // Scan the string backwards from right to left
        for (int i = num.length() - 1; i >= 0; i--) {
            char ch = num.charAt(i);

            // Check if the current character represents an odd digit
            if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9') {
                // Return the substring from index 0 up to (and including) i
                return num.substring(0, i + 1);
            }
        }

        // If no odd digit is found anywhere in the string
        return "";
    }
    public static void main(String []args){
        String s="52";
        System.out.println(largestOddNumber(s));

    }
}
