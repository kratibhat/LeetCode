package leetcode.general;

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
        int number = 121;
        boolean result = solution.isPalindrome(number);
        System.out.println("Is " + number + " a palindrome? " + result); // Output: true
    }
}
