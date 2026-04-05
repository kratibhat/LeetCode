package leetcode.array.easy;

public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0; // Index to place the next non-val element

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {

                nums[count] = nums[i];
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3, 4, 3, 5};
        int val = 3;
        int newLength = removeElement(nums, val);
        System.out.println("New length: " + newLength);
        System.out.print("Array after removing elements equal to " + val + ": ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
