package leetcode.array.houserobber;
//The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
//
//Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
//Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
//
//
//
//Example 1:
//
//
//Input: root = [3,2,3,null,3,null,1]
//Output: 7
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//Example 2:
//
//
//Input: root = [3,4,5,1,3,null,1]
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
//
//
//Constraints
public class HouseRobber3 {
    public int rob(TreeNode root) {
        int[] result = robSubtree(root);
        return Math.max(result[0], result[1]);
    }

    private int[] robSubtree(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = robSubtree(node.left);
        int[] right = robSubtree(node.right);

        int robCurrent = node.val + left[1] + right[1];
        int skipCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{robCurrent, skipCurrent};
    }

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        HouseRobber3 solution = new HouseRobber3();

        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(1);
        System.out.println("Maximum amount of money the thief can rob (Example 1): " + solution.rob(root1)); // Output: 7

        // Example 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);
        System.out.println("Maximum amount of money the thief can rob (Example 2): " + solution.rob(root2)); // Output: 9
    }
}
