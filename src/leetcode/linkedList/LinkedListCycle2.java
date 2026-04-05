package leetcode.linkedList;
//Given the head of a linked list,
// return the node where the cycle begins. If there is no cycle, return null.
//
//There is a cycle in a linked list if
// there is some node in the list that can be reached again by
// continuously following the next pointer.
// Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
// It is -1 if there is no cycle. Note that pos is not passed as a parameter.
//
//Do not modify the linked list.
//
//
//
//Example 1:
//
//
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the second node.
//Example 2:
//
//
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the first node.
//Example 3:
//
//
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
public class LinkedListCycle2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null)
        {
            return null;
        }
        ListNode slow=head;
        ListNode fast= head;
        boolean hasCycle=false;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
            {
                hasCycle=true;
                break;
            }
        }
        if(!hasCycle)
        {
            return null;
        }

        slow=head;
        while(slow!=fast)
        {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        LinkedListCycle2 solution = new LinkedListCycle2();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // Create a cycle here

        ListNode cycleStartNode = solution.detectCycle(head);
        if (cycleStartNode != null) {
            System.out.println("Cycle starts at node with value: " + cycleStartNode.val);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
//Let mu = number of nodes from head to cycle start.
//Let lam = cycle length.
//Let x = how many nodes into the cycle the first meeting point is (distance from cycle start to meeting, going forward).
//When they meet, slow has taken mu + x steps. Fast has taken 2(mu + x) steps. The extra distance fast ran equals an integer number m of full cycles:
//2(mu + x) - (mu + x) = m * lam
//so mu + x = m * lam.
//Rearranged: x = m * lam - mu. Now consider advancing mu steps from the meeting point:
//x + mu = (m * lam - mu) + mu = m * lam — a multiple of the cycle length. That means after exactly mu forward steps from the meeting point you land at the cycle start.
//Therefore: if you reset one pointer to head (it needs mu steps to reach the cycle start) and leave the other at the meeting point (it needs mu steps to reach the cycle start too, as shown), and then move both one step at a time, they will meet at the cycle start after mu steps.
//Example: mu = 2, lam = 3. If m = 1 then x = 3 - 2 = 1. From meeting point to start = lam - x = 2 = mu. So both pointers arrive at cycle start after 2 steps.