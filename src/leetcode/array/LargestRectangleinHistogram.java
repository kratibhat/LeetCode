package leetcode.array;

import java.util.Stack;

public class LargestRectangleinHistogram {
    public int largestRectangleArea1(int[] heights) {
        /// /75ms
        int n=heights.length;
        Stack<Integer> stk=new Stack<>();
        int maxi=0;

        for(int i=0;i<=n;i++){
            int h;
            if(i==n){
                h=-1;
            }else{
                h=heights[i];
            }

            while(!stk.isEmpty() && h<heights[stk.peek()]){
                int height=heights[stk.pop()];
                int right=i;
                int left=stk.isEmpty() ? -1:stk.peek();

                int width=right-left-1;
                int area=height*width;
                maxi=Math.max(maxi,area);
            }

            stk.push(i);
        }
        return maxi;
    }
    /// /9ms
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int nse[] = new int[n];
        for(int i=n-1;i>=0;i--){
            nse[i] = i+1;
            while(nse[i] != n && heights[nse[i]] >= heights[i])
                nse[i] = nse[nse[i]];
        }
        int pse[] = new int[n];
        for(int i=0;i<n;i++){
            pse[i]=i-1;
            while(pse[i] != -1 && heights[pse[i]] >= heights[i])
                pse[i] = pse[pse[i]];
        }
        int ar = 0;
        for(int i=0;i<n;i++){
            int h = heights[i];
            int wid = nse[i] - pse[i] - 1;
            ar = Math.max(ar, h * wid);
        }
        return ar;
    }
        public static void main(String[] args) {
            LargestRectangleinHistogram solution = new LargestRectangleinHistogram();
            int[] heights = {2, 1, 5, 6, 2, 3};
            int result = solution.largestRectangleArea(heights);
            System.out.println("Largest Rectangle Area: " + result); // Output: 10
        }
}
