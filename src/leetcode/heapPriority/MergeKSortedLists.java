package leetcode.heapPriority;


//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//Example 1:
//
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted linked list:
//1->1->2->3->4->4->5->6
public class MergeKSortedLists {
    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();

        ListNode1 l1 = new ListNode1(1, new ListNode1(4, new ListNode1(5)));
        ListNode1 l2 = new ListNode1(1, new ListNode1(3, new ListNode1(4)));
        ListNode1 l3 = new ListNode1(2, new ListNode1(6));

        ListNode1[] lists = new ListNode1[]{l1, l2, l3};

        ListNode1 mergedList = solution.mergeKLists(lists);

        // Print the merged linked list
        printList(mergedList);
    }
    public static void printList(ListNode1 head) {
        ListNode1 current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    public ListNode1 mergeKLists(ListNode1[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i = i + interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);

            }
            interval *= 2;
        }
        return lists[0];

    }

    public ListNode1 mergeTwoLists(ListNode1 l1, ListNode1 l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode1 dummy = new ListNode1(0);
        ListNode1 curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;

    }


}

class ListNode1 {
    public int val;
    public ListNode1 next;

    public ListNode1() {
    }

    public ListNode1(int val) {
        this.val = val;
    }

    public ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }

}
