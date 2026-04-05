package leetcode.Greedy;
//Given two arrays arr[] and dep[], that represent the
// arrival and departure time of i-th train respectively.
// Find the minimum number of platforms required so that no train has to wait.
// If the departure time of one train is the same as the arrival time of another train,
// both trains cannot use the same platform at that time.
//
//Note: Time intervals are in the 24-hour format (HHMM),
// where the first two characters represent hour (between 00 to 23) and the
// last two characters represent minutes (this will be <= 59 and >= 0).
// Leading zeros for hours less than 10 are optional (e.g., 0900 is the same as 900).
public class Minimumnumberofplatfomsrequiredforarailway {
    public static int minPlatform(int[] arr, int[] dep) {
        int n = arr.length;
        int res = 0;

        for (int i = 0; i < n; i++) {

            // Initially one platform is needed
            int cnt = 1;
            for (int j = 0; j < n; j++) {
                if (i != j)

                    // Increment cnt if trains have overlapping
                    // time.
                    if (arr[i] >= arr[j] && dep[j] >= arr[i]) {
                        cnt++;
                    }
            }
            res = Math.max(cnt, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1000, 935, 1100};
        int[] dep = {1200, 1240, 1130};
        System.out.println(minPlatform(arr, dep));
    }
}
