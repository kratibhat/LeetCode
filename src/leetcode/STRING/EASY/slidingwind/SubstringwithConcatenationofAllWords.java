package leetcode.STRING.EASY.slidingwind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//You are given a string s and an array of strings words. All the strings of words are of the same length.
//
//A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
//
//For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
//Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//
//Output: [0,9]
//
//Explanation:
//
//The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
//The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
//
//Example 2:
//
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//
//Output: []
//
//Explanation:
//
//There is no concatenated substring.
//
//Example 3:
//
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//
//Output: [6,9,12]
//
//Explanation:
//
//The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
//The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
//The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
//
//
public class SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // Step 1: Build the master frequency map of required words
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Run a sliding window for each possible word-length offset
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentMap = new HashMap<>();
            int count = 0; // Tracks the number of valid words currently in the window

            // Slide across the string in steps of wordLen
            while (right + wordLen <= sLen) {
                // Extract the word at the current right boundary
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordCountMap.containsKey(word)) {
                    currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more occurrences of 'word' than required,
                    // shrink the window from the left until it's valid
                    while (currentMap.get(word) > wordCountMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If the number of valid words matches wordCount, we found a match
                    if (count == wordCount) {
                        result.add(left);

                        // Shift left forward by one word to keep looking
                        String leftWord = s.substring(left, left + wordLen);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                } else {
                    // Mismatch found: Reset the window completely
                    currentMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;

    }
    public static void main(String[] args) {
        SubstringwithConcatenationofAllWords solution = new SubstringwithConcatenationofAllWords();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> result = solution.findSubstring(s, words);
        System.out.println("Starting indices of substring(s) that are a concatenation of all words: " + result); // Output: [0, 9]
    }

}
