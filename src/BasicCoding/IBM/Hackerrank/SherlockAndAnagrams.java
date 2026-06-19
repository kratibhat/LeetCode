package BasicCoding.IBM.Hackerrank;
import java.util.Arrays;
import java.util.HashMap;
public class SherlockAndAnagrams {


    public static int sherlockAndAnagrams(String s) {
        HashMap<String, Integer> substringCounts = new HashMap<>();
        int n = s.length();

        // Step 1 & 2: Generate all substrings and sort them
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);

                // Convert to char array, sort, and convert back to string
                char[] chars = substring.toCharArray();
                Arrays.sort(chars);
                String sortedSubstring = new String(chars);

                // Step 3: Count frequencies
                substringCounts.put(sortedSubstring, substringCounts.getOrDefault(sortedSubstring, 0) + 1);
            }
        }

        // Step 4: Calculate total pairs
        int totalPairs = 0;
        for (int count : substringCounts.values()) {
            if (count > 1) {
                totalPairs += (count * (count - 1)) / 2;
            }
        }

        return totalPairs;
    }
    public static void main(String[] args) {
        String s = "abba";
        int result = sherlockAndAnagrams(s);
        System.out.println("Total anagrammatic pairs: " + result); // Output: 4
    }
}
