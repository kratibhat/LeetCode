package leetcode.array.easy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class ConvertSortedArrayToBST {

    public static void main(String[] args) {
        ConvertSortedArrayToBST converter = new ConvertSortedArrayToBST();
        int[] nums = {-10,-3,0,5,9};
        TreeNode bstRoot = converter.sortedArrayToBST(nums);

        List<Integer> serialized = converter.serialize(bstRoot);
        System.out.println(serialized); // prints [0, -3, 9, -10, null, 5]
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return getBST(nums, 0, nums.length - 1);
    }

    private TreeNode getBST(int[] nums, int st, int ei) {
        if (st > ei) return null;
        // pick the upper middle to match expected shape
        int mi = st + (ei - st + 1) / 2;
        TreeNode node = new TreeNode(nums[mi]);
        node.left = getBST(nums, st, mi - 1);
        node.right = getBST(nums, mi + 1, ei);
        return node;
    }

    private List<Integer> serialize(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n != null) {
                res.add(n.val);
                q.add(n.left);
                q.add(n.right);
            } else {
                res.add(null);
            }
        }
        // trim trailing nulls
        int i = res.size() - 1;
        while (i >= 0 && res.get(i) == null) {
            res.remove(i);
            i--;
        }
        return res;
    }
}



