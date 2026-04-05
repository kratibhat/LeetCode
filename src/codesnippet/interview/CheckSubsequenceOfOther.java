package codesnippet.interview;

public class CheckSubsequenceOfOther {
    // Returns true if s1 is a subsequence of s2
    public boolean isSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s1.length();
    }

    // Example usage
    public static void main(String[] args) {
        CheckSubsequenceOfOther checker = new CheckSubsequenceOfOther();
        String s1 = "abc";
        String s2 = "aebdc";
        System.out.println(checker.isSubsequence(s1, s2)); // true
        System.out.println(checker.isSubsequence("axc", s2)); // false
    }
}
