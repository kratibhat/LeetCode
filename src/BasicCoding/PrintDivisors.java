package BasicCoding;

public class PrintDivisors {
    public static void printDivisors(int n) {
        // We only loop up to the square root of n
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");

                // If the divisors are not the same (to avoid perfect squares)
                if (i != n / i) {
                    System.out.print((n / i) + " ");
                }
            }
        }
    }
    public static void main(String[] args) {
        int n = 36;
        System.out.println("Divisors of " + n + ":");
        printDivisors(n);
    }
}
