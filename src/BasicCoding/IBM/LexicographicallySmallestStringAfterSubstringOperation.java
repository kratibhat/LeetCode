package BasicCoding.IBM;

//Given a string s consisting of lowercase English letters. Perform the following operation:
//
//Select any non-empty substring then replace every letter of the substring with the preceding letter of the English alphabet. For example, 'b' is converted to 'a', and 'a' is converted to 'z'.
//Return the lexicographically smallest string after performing the operation.
public class LexicographicallySmallestStringAfterSubstringOperation {
    public static String smallestString(String s) {
        int n = s.length();
        StringBuilder str = new StringBuilder(s);
        int ind = 0;

        while (ind < n && s.charAt(ind) == 'a') ind++;

        if (ind == n)
        {
            str.setCharAt(n - 1, 'z');
        }
        else
        {
            while (ind < n && s.charAt(ind) != 'a')
            {
                str.setCharAt(ind, (char)(s.charAt(ind++) - 1));
            }
        }

        return str.toString();
    }
    public static String getSmallestString(String s) {

        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;

        // Step 1: Skip leading 'a's
        while (i < n && chars[i] == 'a') {
            i++;
        }

        // Step 3 (Edge Case): If all characters are 'a', we must change the last one
        if (i == n) {
            chars[n - 1] = 'z';
            return new String(chars);
        }

        // Step 2: Change characters until we hit an 'a' or the end of the string
        while (i < n && chars[i] != 'a') {
            chars[i] = (char) (chars[i] - 1);
            i++;
        }

        return new String(chars);
    }
    // Use this editor to write, compile and run your Java code online


        public static String getSmallestString1(String s){
            char[] charArray = s.toCharArray();
            boolean modified = false;

            // Traverse the string to find the first character greater than 'a'
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == 'a') {
                    // If we have already modified the string, stop further changes
                    if (modified) {
                        break;
                    }
                    continue;
                }
                // Start changing characters from here
                modified = true;

                charArray[i] = (char) (charArray[i] - 1);
            }

            if (!modified) {
                charArray[charArray.length - 1] = (char) (charArray[charArray.length - 1] - 1);
            }

            // Convert the character array back to string and return
            return new String(charArray);
        }


    public static void main(String[] args) {
        // Test with the example from the screenshot
        System.out.println(getSmallestString("hackerrank"));
        System.out.println(getSmallestString("aaaa"));
        System.out.println(getSmallestString1("aaaa"));
        System.out.println(smallestString("cbabc"));

        // Output: gackerrank
    }
}