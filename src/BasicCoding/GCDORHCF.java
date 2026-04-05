package BasicCoding;

public class GCDORHCF {
    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    // Driver code
    public static void main(String[] args) {
        int a = 20, b = 28;
        System.out.println(gcd(a, b));
    }
}
