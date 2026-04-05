package leetcode.STRING.EASY;
//Given two version strings, version1 and version2, compare them. A version string consists of revisions separated by dots '.'. The value of the revision is its integer conversion ignoring leading zeros.
//
//To compare version strings, compare their revision values in left-to-right order. If one of the version strings has fewer revisions, treat the missing revision values as 0.
//
//Return the following:
//
//If version1 < version2, return -1.
//If version1 > version2, return 1.
//Otherwise, return 0.
//
//
//Example 1:
//
//Input: version1 = "1.2", version2 = "1.10"
//
//Output: -1
//
//Explanation:
//
//version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.
//
//Example 2:
//
//Input: version1 = "1.01", version2 = "1.001"
//
//Output: 0
//
//Explanation:
//
//Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
//
//Example 3:
//
//Input: version1 = "1.0", version2 = "1.0.0.0"
//
//Output: 0
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] revisions1 = version1.split("\\.");
        String[] revisions2 = version2.split("\\.");

        int maxLength = Math.max(revisions1.length, revisions2.length);

        for (int i = 0; i < maxLength; i++) {
            int rev1 = i < revisions1.length ? Integer.parseInt(revisions1[i]) : 0;
            int rev2 = i < revisions2.length ? Integer.parseInt(revisions2[i]) : 0;

            if (rev1 < rev2) {
                return -1;
            } else if (rev1 > rev2) {
                return 1;
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        CompareVersionNumbers solution = new CompareVersionNumbers();
        String version1 = "1.2";
        String version2 = "1.10";
        int result = solution.compareVersion(version1, version2);
        System.out.println("Comparison result: " + result); // Output: -1
    }
}
