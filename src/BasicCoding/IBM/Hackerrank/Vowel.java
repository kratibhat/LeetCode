package BasicCoding.IBM.Hackerrank;

import java.util.*;

public class Vowel {
    public static String convertToPigLatin(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        // Convert word to lowercase to easily handle case-insensitive checks
        String lowerWord = word.toLowerCase();
        int n = lowerWord.length();
        int firstVowelIndex = -1;

        // Step 1: Find the index of the first vowel
        for (int i = 0; i < n; i++) {
            char ch = lowerWord.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                firstVowelIndex = i;
                break; // Vowel found, stop scanning
            }
        }

        // Edge Case: If no vowel is found, the rules imply adding "ay" to the end
        if (firstVowelIndex == -1) {
            return word + "ay";
        }

        // Step 2 & 3: Split and rearrange substrings
        // Substring from the first vowel to the end
        String remainingPart = word.substring(firstVowelIndex);
        // Substring containing all consonants before the first vowel
        String leadingConsonants = word.substring(0, firstVowelIndex);

        return remainingPart + leadingConsonants + "ay";
    }

    public static void main(String[] args) {
        // Test with the example from the screenshot
        System.out.println(convertToPigLatin("cheese")); // Output: eesechay
        System.out.println(convertToPigLatin("apple"));  // Output: appleay (Starts with vowel)
    }
}