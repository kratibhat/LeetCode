package leetcode.STRING.EASY;

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
