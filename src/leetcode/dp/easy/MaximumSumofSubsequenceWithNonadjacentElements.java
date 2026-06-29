package leetcode.dp.easy;
/// /You are given an array nums consisting of integers. You are also given a 2D array queries, where queries[i] = [posi, xi].
///
// For query i, we first set nums[posi] equal to xi, then we calculate the answer to query i which is the maximum sum of a subsequence of nums where no two adjacent elements are selected.
///
/// Return the sum of the answers to all queries.
///
/// Since the final answer may be very large, return it modulo 109 + 7.
///
/// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
///
///
///
/// Example 1:
///
/// Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]
///
/// Output: 21
///
/// Explanation:
/// After the 1st query, nums = [3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 3 + 9 = 12.
/// After the 2nd query, nums = [-3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 9.
///
/// Example 2:
///
/// Input: nums = [0,-1], queries = [[0,-5]]
///
/// Output: 0
///
/// Explanation:
/// After the 1st query, nums = [-5,-1] and the maximum sum of a subsequence with non-adjacent elements is 0 (choosing an empty subsequence).
///
///
public class MaximumSumofSubsequenceWithNonadjacentElements {
    private static final int MOD = 1_000_000_007;

    class Node {
        // f[i][j]: i=1 if left boundary is used, j=1 if right boundary is used
        long f00, f01, f10, f11;

        Node() {
            this.f00 = this.f01 = this.f10 = this.f11 = 0;
        }

        void set(int val) {
            this.f00 = 0;
            this.f01 = 0;
            this.f10 = 0;
            this.f11 = Math.max(0, val);
        }
    }

    private Node[] tree;
    private int n;

    private void merge(Node res, Node a, Node b) {
        res.f00 = Math.max(a.f00 + b.f10, a.f01 + b.f00);
        res.f01 = Math.max(a.f00 + b.f11, a.f01 + b.f01);
        res.f10 = Math.max(a.f10 + b.f10, a.f11 + b.f00);
        res.f11 = Math.max(a.f10 + b.f11, a.f11 + b.f01);
    }

    private void build(int[] nums, int node, int start, int end) {
        tree[node] = new Node();

        if (start == end) {
            tree[node].set(nums[start]);
            return;
        }

        int mid = (start + end) / 2;

        build(nums, 2 * node, start, mid);
        build(nums, 2 * node + 1, mid + 1, end);
        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node].set(val);
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid)
            update(2 * node, start, mid, idx, val);
        else
            update(2 * node + 1, mid + 1, end, idx, val);

        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        n = nums.length;
        tree = new Node[4 * n];
        build(nums, 1, 0, n - 1);

        long totalSum = 0;
        for (int[] q : queries) {
            update(1, 0, n - 1, q[0], q[1]);
            // The answer for the whole array is the max of the root node's 4 states
            long maxSub = Math.max(Math.max(tree[1].f00, tree[1].f01),
                    Math.max(tree[1].f10, tree[1].f11));
            totalSum = (totalSum + maxSub) % MOD;
        }

        return (int) totalSum;
    }
    public static void main(String [] args){
        int []nums={3,5,9};
        int[][] queries = {
                {1, -2},
                {0, -3}
        };
        MaximumSumofSubsequenceWithNonadjacentElements maximumSumofSubsequenceWithNonadjacentElements=new MaximumSumofSubsequenceWithNonadjacentElements();
        int res= maximumSumofSubsequenceWithNonadjacentElements.maximumSumSubsequence(nums,queries);
        System.out.println(res);
    }
}
