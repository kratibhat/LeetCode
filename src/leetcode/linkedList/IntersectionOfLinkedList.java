package leetcode.linkedList;
//Given the heads of two singly linked-lists headA and headB, return the node at which
// the two lists intersect. If the two linked lists have no intersection at all, return null.
//
//For example, the following two linked lists begin to intersect at node c1:
//
//
//The test cases are generated such that there are no cycles anywhere in the entire
// linked structure.
//
//Note that the linked lists must retain their original structure after the function returns.
//
//Custom Judge:
//
//The inputs to the judge are given as follows (your program is not given these inputs):
//
//intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
//listA - The first linked list.
//listB - The second linked list.
//skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
//skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
//The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
//
//Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
//Output: Intersected at '8'
//Explanation: The intersected node's value is 8 (note that this must not be 0 if the
// two lists intersect).
//From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as
// [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A;
// There are 3 nodes before the intersected node in B.
//- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references.
// In other words, they point to two different locations in memory, while the nodes with value
// 8 in A and B (3rd node in A and 4th node in B) point to the same location in
public class IntersectionOfLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;
//the next node should be same reference not the previous node
        //for ex 3-->4-->5 and 2-->4-->5
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA;
    }
    public static void main(String[] args) {
        IntersectionOfLinkedList solution = new IntersectionOfLinkedList();

        // Creating two linked lists that intersect
        ListNode common = solution.new ListNode(8);
        common.next = solution.new ListNode(4);
        common.next.next = solution.new ListNode(5);

        ListNode headA = solution.new ListNode(4);
        headA.next = solution.new ListNode(1);
        headA.next.next = common;

        ListNode headB = solution.new ListNode(5);
        headB.next = solution.new ListNode(6);
        headB.next.next = solution.new ListNode(1);
        headB.next.next.next = common;


        ListNode head1 = solution.new ListNode(4);
        head1.next = solution.new ListNode(1);


        ListNode headB1= solution.new ListNode(5);
        headB1.next = solution.new ListNode(0);
        headB1.next.next = solution.new ListNode(1);


        ListNode intersection = solution.getIntersectionNode(headA, headB);
        ListNode intersection1 = solution.getIntersectionNode(head1, headB1);
        if(intersection1 != null){
            System.out.println("Intersection at node with value: " + intersection1.val);
        }else {
            System.out.println("No intersection.");
        }
        if (intersection != null) {
            System.out.println("Intersection at node with value: " + intersection.val); // Output: 8
        } else {
            System.out.println("No intersection.");
        }
    }

}
//Initialize two pointers at the heads.
//In each loop step advance each pointer by one node.
// If a pointer reaches null, redirect it to the other list's head.
//Loop stops when pointers are equal (same reference) — that will be the intersection node — or when both become null (no intersection).
//Why it works: by switching heads when reaching the end,
// each pointer traverses exactly lengthA + lengthB nodes.
// If the lists intersect, the two pointers align and arrive at the
// shared node at the same time; otherwise both hit null after the full traversal.
//Complexity: O(m + n) time, O(1) extra space.
//Short example:
//A: nodes a0→a1→c0→c1
//B: nodes b0→b1→b2→c0→c1
//PointerA path: a0→a1→c0→c1→b0→b1→b2→c0...
//PointerB path: b0→b1→b2→c0→c1→a0→a1→c0...
//They meet at c0 (first common node).