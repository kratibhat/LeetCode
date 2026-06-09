package codesnippet.interview;

import java.util.Arrays;
import java.util.List;

public class LetterStartWithAUsingStreams {
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

    public void printNamesStartingWithA() {
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        LetterStartWithAUsingStreams example = new LetterStartWithAUsingStreams();
        example.printNamesStartingWithA();
    }
}
