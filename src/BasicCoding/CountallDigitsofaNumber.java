package BasicCoding;

public class CountallDigitsofaNumber {
    public static void main(String[] args) {
        int number = 12345;
        int count = countDigits(number);
        System.out.println("Number of digits: " + count);
    }

    public static int countDigits(int n) {
        // Handle the case where the number is 0
        if (n == 0) return 1;

        // Make the number positive to handle negative inputs
        n = Math.abs(n);
        
        int count = 0;
        while (n > 0) {
            n = n / 10; // Remove the last digit
            count++;    // Increment the counter
        }
        return count;
    }
}