package BasicCoding.IBM.Hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Extracted Question: Spam Text ClassificationYou are given:texts[n]: An array of text strings to classify.spamWords[k]: An array of words considered spam indicators.Classify each text as "spam" or "not_spam" using these rules:A text is "spam" if it contains at least 2 occurrences of words that appear in spamWords.A text is "not_spam" if it contains fewer than 2 such occurrences.Matching is case-insensitive.Words are separated by single spaces (treat each token as a word).Each occurrence counts, including repeated spam words in the same text (for example, "paid paid" counts as 2 occurrences).Only consider words that exactly match a spam word (no partial matches).Return an array of length $n$, where the $i^{th}$ value is the label for texts[i].
public class SpamDetection {

        public static List<String> classifyTexts(List<String> texts, List<String> spamWords) {
            List<String> result = new ArrayList<>();

            // Step 1: Populate the HashSet with lowercase spam words for O(1) lookups
            Set<String> spamSet = new HashSet<>();
            for (String word : spamWords) {
                if (word != null) {
                    spamSet.add(word.toLowerCase());
                }
            }

            // Step 2: Process each text log individually
            for (String text : texts) {
                int spamCount = 0;

                // Handle edge case of empty or null strings
                if (text == null || text.trim().isEmpty()) {
                    result.add("not_spam");
                    continue;
                }

                // Split the text string by spaces into single word tokens
                String[] tokens = text.toLowerCase().split(" ");

                for (String token : tokens) {
                    if (spamSet.contains(token)) {
                        spamCount++;
                    }
                }

                // Step 3: Apply the threshold classification rule
                if (spamCount >= 2) {
                    result.add("spam");
                } else {
                    result.add("not_spam");
                }
            }

            return result;
        }
        public static void main(String[] args) {
            List<String> texts = List.of(
                    "This is a spam message",
                    "Buy now and get 50% off",
                    "Hello, how are you?",
                    "Limited time offer, buy now!"
            );

            List<String> spamWords = List.of("spam", "buy", "offer", "discount");

            List<String> classifications = classifyTexts(texts, spamWords);
            for (int i = 0; i < texts.size(); i++) {
                System.out.println("Text: \"" + texts.get(i) + "\" is classified as: " + classifications.get(i));
            }
        }
    }
