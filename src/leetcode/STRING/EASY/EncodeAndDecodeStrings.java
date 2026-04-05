package leetcode.STRING.EASY;

import java.util.List;

//Given an array of strings strs, encode the array to a string, and then decode
// the string to get the original array of strings.
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(java.util.List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append(str.length()).append('#').append(str);
        }
        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
     List<String> decoded = new java.util.ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            decoded.add(s.substring(j + 1, j + 1 + length));
            i = j + 1 + length;
        }
        return decoded;
    }

    public static void main(String[] args) {
        EncodeAndDecodeStrings codec = new EncodeAndDecodeStrings();
        java.util.List<String> original = java.util.Arrays.asList("hello", "world", "this", "is", "a", "test");
        String encoded = codec.encode(original);
        System.out.println("Encoded: " + encoded);
        java.util.List<String> decoded = codec.decode(encoded);
        System.out.println("Decoded: " + decoded);
    }
}
