package leetcode.array.FrogJump;

import java.util.*;

//Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
//
//The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
//
//Return the probability that after t seconds the frog is on the vertex target. Answers within 10-5 of the actual answer will be accepted.
//
//
//
//Example 1:
//
//
//Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
//Output: 0.16666666666666666
//Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after second 1 and then jumping with 1/2 probability to vertex 4 after second 2. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666.
//Example 2:
//
//
//Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
//Output: 0.3333333333333333
//Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1.
//
public class FrogPositionAfterTSeconds {
    List<Integer>[] graph;
    boolean[] visited;

    public double frogPosition(int n, int[][] edges, int t, int target) {

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        // Build graph (tree)
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        visited = new boolean[n + 1];

        return dfs(1, t, target, 1.0);
    }

    private double dfs(int node, int t, int target, double prob) {

        visited[node] = true;

        // If time is over
        if (t == 0) {
            return node == target ? prob : 0.0;
        }

        // Count unvisited children
        int children = 0;
        for (int nei : graph[node]) {
            if (!visited[nei]) children++;
        }

        // If no children, frog stays here
        if (children == 0) {
            return node == target ? prob : 0.0;
        }

        double ans = 0.0;
        for (int nei : graph[node]) {
            if (!visited[nei]) {
                ans += dfs(nei, t - 1, target, prob / children);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        FrogPositionAfterTSeconds solution = new FrogPositionAfterTSeconds();
        int n = 7;
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        int t = 2;
        int target = 4;
        double result = solution.frogPosition(n, edges, t, target);
        System.out.println("Probability: " + result); // Output: 0.16666666666666666
    }
}
