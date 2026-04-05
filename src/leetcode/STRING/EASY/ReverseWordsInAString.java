package leetcode.STRING.EASY;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                reversed.append(" ");
            }
        }

        return reversed.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInAString reverser = new ReverseWordsInAString();
        String s = "  Hello   World  ";
        String result = reverser.reverseWords(s);
        System.out.println("Reversed words: \"" + result + "\"");
    }
}
