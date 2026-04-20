package guessOutput;

public class StringComparison {
    public static void main(String[] args) {
        String s1 = "Hello";              // Stored in String Pool
        String s2 = "Hello";              // Points to the same "Hello" in Pool
        String s3 = new String("Hello");  // Forced into Heap (New Object)
        String s4 = "HELLO";              // Different case

        // 1. Content comparison (The actual text)
        System.out.println(s1.equals(s2));        // Output: true
        System.out.println(s1.equals(s3));        // Output: true
        
        // 2. Case-sensitive comparison
        System.out.println(s1.equals(s4));        // Output: false
        System.out.println(s1.equalsIgnoreCase(s4)); // Output: true

        // 3. Memory Reference comparison (For context)
        System.out.println(s1 == s2);             // Output: true (Same pool address)
        System.out.println(s1 == s3);             // Output: false (Pool vs Heap)
    }
}