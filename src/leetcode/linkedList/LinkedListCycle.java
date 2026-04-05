package leetcode.linkedList;
//Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//There is a cycle in a linked list if there is some node in the
// list that can be reached again by continuously following the next pointer.
// Internally, pos is used to denote the index of the node that tail's next pointer
// is connected to. Note that pos is not passed as a parameter.
//
//Return true if there is a cycle in the linked list. Otherwise, return false.

//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list,
// where the tail connects to the 1st node (0-indexed).
public class LinkedListCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { //fast is used bcoz it moves two steps at a time
            // for case where there is no cycle if slow moves one step and fast moves two steps it will reach null first
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){
        LinkedListCycle solution = new LinkedListCycle();

        // Creating a linked list with a cycle for testing
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // Creating a cycle here

        boolean result = solution.hasCycle(head);
        System.out.println("Linked List has cycle: " + result); // Expected output: true
    }
}
