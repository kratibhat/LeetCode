package leetcode.STRING.EASY;
//rite a code to describe this as follows:
// Rules:Find the first vowel (a, e, i, o, u).Move all consonants before that
// vowel to the end of the word.Add "ay" to the end.Step-by-step
// Example for "cheese":Word: cheeseFirst vowel: e (at 0-indexed position 2, or 1-based position 3)
// Consonants before it: ch Remaining part starting from the first vowel: eeseMove consonants to the end: eesechAdd "ay": eesechayResult: "cheese" $\rightarrow$ "eesechay"
public class FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        int[] charCount = new int[26];

        // Count the frequency of each character
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Find the index of the first unique character
        for (int i = 0; i < s.length(); i++) {
            if (charCount[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1; // No unique character found
    }

    public static void main(String[] args) {
        FirstUniqueCharacterinaString solution = new FirstUniqueCharacterinaString();
        String s = "leetcode";
        int result = solution.firstUniqChar(s);
        System.out.println("The index of the first unique character in \"" + s + "\" is: " + result);
    }
}
