package leetcode.Greedy;

import java.util.Arrays;

//Assign Cookies
//Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
//
//Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
//
//
//
//Example 1:
//
//Input: g = [1,2,3], s = [1,1]
//Output: 1
//Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
//And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
//You need to output 1.
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        // Step 1: Sort both arrays
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0; // Pointer for children (greed factor)
        int j = 0; // Pointer for cookies (size)

        // Step 2: Iterate through cookies and children
        while (i < g.length && j < s.length) {
            // If the cookie satisfies the child's greed
            if (s[j] >= g[i]) {
                i++; // This child is content, move to the next child
            }
            // Move to the next cookie regardless
            j++;
        }

        // The number of content children is represented by the index i
        return i;
    }
    public static void main(String[] args) {
        AssignCookies solution = new AssignCookies();
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        int result = solution.findContentChildren(g, s);
        System.out.println("Maximum number of content children: " + result); // Output: 1
    }
}