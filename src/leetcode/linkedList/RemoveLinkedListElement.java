package leetcode.linkedList;
//Input: head = [1,2,6,3,4,5,6], val = 6
//Output: [1,2,3,4,5]
public class RemoveLinkedListElement {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
    //this method will not work because current is bcming current.next so current is moving and hence
    //list will be empty at last
    public ListNode removeElements1(ListNode head, int val) {
        if(head==null)
        {
            return head;
        }
        if(head.val==val)
        {
            return head.next;
        }
        ListNode current=head;
        while(current.next!=null)
        {
            if(current.next.val==val)
            {
                current.next=current.next.next;
            }
            else
            {
                current=current.next;
            }
        }
        return current.next;


    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }
    public static void main(String[] args) {
        RemoveLinkedListElement solution = new RemoveLinkedListElement();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        int valToRemove = 6;
        ListNode updatedHead = solution.removeElements(head, valToRemove);

        // Print the updated linked list
        ListNode current = updatedHead;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
