package leetcode.STRING.EASY.dp;
//Given an integer x, return true if x is a palindrome, and false otherwise.
//
//
//
//Example 1:
//
//Input: x = 121
//Output: true
//Explanation: 121 reads as 121 from left to right and from right to left.
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int original = x;
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return original == reversed;
    }

    public static void main(String[] args) {
        PalindromeNumber solution = new PalindromeNumber();
        int x = 121;
        boolean result = solution.isPalindrome(x);
        System.out.println("Is " + x + " a palindrome? " + result); // Output: true
    }

}
