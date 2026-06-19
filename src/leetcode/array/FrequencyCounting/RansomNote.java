package leetcode.array.FrequencyCounting;
//Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//Each letter in magazine can only be used once in ransomNote.
//
//
//
//Example 1:
//
//Input: ransomNote = "a", magazine = "b"
//Output: false
//Example 2:
//
//Input: ransomNote = "aa", magazine = "ab"
//Output: false
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Since we are dealing only with lowercase English letters,
        // a fixed-size frequency array of size 26 acts as our optimal bucket map.
        int[] charCounts = new int[26];

        // Step 1: Count the frequencies of all characters available in the magazine
        for (int i = 0; i < magazine.length(); i++) {
            charCounts[magazine.charAt(i) - 'a']++;
        }

        // Step 2: Consume the characters to construct the ransomNote
        for (int i = 0; i < ransomNote.length(); i++) {
            int targetCharIndex = ransomNote.charAt(i) - 'a';

            // Decrement the available count for this character
            charCounts[targetCharIndex]--;

            // If the count drops below 0, it means the magazine didn't have enough
            // copies of this letter to complete the note.
            if (charCounts[targetCharIndex] < 0) {
                return false;
            }
        }

        // If we successfully matched every letter, return true
        return true;
    }
    public static void main(String[] args) {
        RansomNote solution = new RansomNote();
        String ransomNote = "aa";
        String magazine = "ab";
        boolean result = solution.canConstruct(ransomNote, magazine);
        System.out.println("Can construct ransom note: " + result); // Output: false
    }
}
