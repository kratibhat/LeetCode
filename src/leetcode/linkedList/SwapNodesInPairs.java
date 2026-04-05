package leetcode.linkedList;
//Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
//
//
//
//Example 1:
//
//Input: head = [1,2,3,4]
//
//Output: [2,1,4,3]
public class SwapNodesInPairs {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next; // The new head will be the second node

        ListNode prev = null;
        ListNode current = head;

        while (current != null && current.next != null) {
            ListNode nextPair = current.next.next; // Store the next pair
            ListNode second = current.next;

            // Swap the pair
            second.next = current;
            current.next = nextPair;

            // Connect the previous pair to the current swapped pair
            if (prev != null) {
                prev.next = second;
            }

            // Move prev and current pointers forward
            prev = current;
            current = nextPair;
        }

        return newHead;
    }
    public static void main(String[] args) {
        SwapNodesInPairs solution = new SwapNodesInPairs();

        // Creating a linked list for testing: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
       head.next.next.next = new ListNode(4);

        ListNode swappedHead = solution.swapPairs(head);
        // Print the swapped linked list
        ListNode current = swappedHead;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");





        // Test case with odd number of nodes: 1 -> 2 -> 3
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        ListNode swappedHead1 = solution.swapPairs(head1);
        ListNode current2 = swappedHead1;
        while (current2 != null) {
            System.out.print(current2.val + " -> ");
            current2 = current2.next;
        }
        System.out.println("null");




    }

}
