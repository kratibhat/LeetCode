package BasicCoding.IBM;

public class BetterCompression {
    //Read more at: https://jzleetcode.github.io/posts/leet-3167-hackerrank-better-compression/
    public String betterCompression(String s) {
        int[] res = new int[26];
        int n = s.length(), i = 0;
        while (i < n) {
            int c = s.charAt(i) - 'a';
            i++;
            int cnt = 0;
            while (i < n && Character.isDigit(s.charAt(i))) {
                cnt = cnt * 10 + (s.charAt(i) - '0');
                i++;
            }
            res[c] += cnt;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            if (res[j] > 0) {
                sb.append((char) ('a' + j)).append(res[j]);
            }
        }
        return sb.toString();
    }
    public static  void main(String[] args) {
       String s = "a1c1b2c3";
       BetterCompression bc = new BetterCompression();
       String result = bc.betterCompression(s);
       System.out.println(result); // Output: "a1b2c4"
    }
   // Read more at: https://jzleetcode.github.io/posts/leet-3167-hackerrank-better-compression/
}
