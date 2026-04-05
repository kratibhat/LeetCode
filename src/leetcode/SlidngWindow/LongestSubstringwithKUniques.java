package leetcode.SlidngWindow;
//You are given a string s consisting only lowercase alphabets and an integer k. Your task is to find the length of the longest substring that contains exactly k distinct characters.
//gfg
//Note : If no such substring exists, return -1.
public class LongestSubstringwithKUniques {
    public int longestKSubstr(String s, int k) {
        int[] freq = new int[26];
        int l=0;
        int unique = 0;
        int max_len = -1;
        for(int r = 0; r< s.length() ; r++){
            if(freq[s.charAt(r)-'a'] == 0)unique++;
            freq[s.charAt(r)-'a']++;

            while(unique > k){
                freq[s.charAt(l)-'a']--;
                if(freq[s.charAt(l)-'a'] == 0)unique--;
                l++;
            }

            if(unique == k)
                max_len = Math.max( max_len,r-l+1);
        }
        return max_len;
    }
    public static void main(String[] args) {
        LongestSubstringwithKUniques solution = new LongestSubstringwithKUniques();
        String s = "aabacbebebe";
        int k = 3;
        String a="aa"; //to check dfrnce between to 2 codes of LongestSubstringwithAtMostKUniqueCharecters
        int l=2;
        int result = solution.longestKSubstr(s, k);
        int res=solution.longestKSubstr(a, l);
         System.out.println("Length of the longest substring with exactly " + l + " distinct characters: " + res);
        System.out.println("Length of the longest substring with exactly " + k + " distinct characters: " + result);
    }

}
