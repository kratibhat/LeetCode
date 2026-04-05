package codesnippet.interview;

public class VowelConsonantCount {
    public static void main(String[] args) {
        String input = "Hello World";
        int vowelCount = 0;
        int consonantCount = 0;

        for (char c : input.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') { // Check if the character is a letter
                if ("aeiou".indexOf(c) != -1) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }

        System.out.println("Vowels: " + vowelCount);
        System.out.println("Consonants: " + consonantCount);
    }
}
