package leetcode.STRING.EASY.twopointer;

public class ValidPalindrome2 {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // Mismatch found! Test both single-character deletion paths
                return isPalindromeRange(s, left + 1, right) ||
                        isPalindromeRange(s, left, right - 1);
            }
        }

        // If we complete the loop without any mismatch, it's already a perfect palindrome
        return true;
    }

    private boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        ValidPalindrome2 solution = new ValidPalindrome2();
        String s = "abca";
        boolean result = solution.validPalindrome(s);
        System.out.println("Can the string be a valid palindrome by removing at most one character? " + result); // Output: true
    }
}
