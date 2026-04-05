package leetcode.linkedList;

public class RemoveNthNodeFromEndOfList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
//A dummy node is created and points to head.
// This simplifies deletion when the head itself must be removed
// (dummy lets you always delete via second.next = second.next.next).
//Initialize first and second to dummy.
//Advance first exactly n + 1 steps. After this, the distance (number of nodes) between
// first and second is n.
//Move both pointers forward together until first reaches null.
//Because of the fixed gap, when first is null, second is positioned
// immediately before the node to remove (the n‑th from the end).
//Remove the target by linking around it: second.next = second.next.next.
//Return dummy.next (the possibly new head).
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        // Move first n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Remove the nth node from the end
        second.next = second.next.next;

        return dummy.next;
    }
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

        // Creating a linked list for testing: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = solution.new ListNode(1);
        head.next = solution.new ListNode(2);
        head.next.next = solution.new ListNode(3);
        head.next.next.next = solution.new ListNode(4);
        head.next.next.next.next = solution.new ListNode(5);

        int n = 2; // Remove the 2nd node from the end

        ListNode modifiedHead = solution.removeNthFromEnd(head, n);

        // Print the modified linked list
        ListNode current = modifiedHead;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
