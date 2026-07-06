package leetcode.Binary.BSonAnswers;

//X^N=M
public class FindNthRootOfM {
    public static void main(String[] args) {
        FindNthRootOfM obj = new FindNthRootOfM();
        int n = 3; // Example: 3rd root
        int m = 27; // Example: 27
        int result = obj.nthRoot(n, m);
        System.out.println("The " + n + "th root of " + m + " is: " + result);
    }

    public int nthRoot(int n, int m) {
        // Set low and high for binary search
        int low = 1, high = m;

        // Start binary search
        while (low <= high) {
            // Calculate mid
            int mid = (low + high) / 2;

            // Store result of mid^n
            long ans = 1;
            for (int i = 0; i < n; i++) {
                ans *= mid;
                if (ans > m) break;
            }

            // If mid^n equals m
            if (ans == m) return mid;

            // If mid^n is less than m
            if (ans < m) low = mid + 1;

                // If mid^n is more than m
            else high = mid - 1;
        }

        // Return -1 if not found
        return -1;
    }
}
