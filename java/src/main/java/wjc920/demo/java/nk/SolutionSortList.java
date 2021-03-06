package wjc920.demo.java.nk;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class SolutionSortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(19);
        head.next.next = new ListNode(14);
        head.next.next.next = new ListNode(5);
//        {4,19,14,5,-3,1,8,5,11,15}
        head.next.next.next.next = new ListNode(-3);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next = new ListNode(11);
        head.next.next.next.next.next.next.next.next.next = new ListNode(15);
        ListNode newHead = new SolutionSortList().insertSort(head);
        ListNode cur=newHead;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode cur = head;
        if (head == null) {
            return null;
        }
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        head = mergeSort(head, n);
        cur = head;
        while (cur != null) {
            cur = cur.next;
        }
        return head;
    }

    private ListNode mergeSort(ListNode head, int len) {
        if (len <= 1) {
            head.next = null;
            return head;
        }
        int newLen = len / 2;
        ListNode curHead = head;
        ListNode mid = curHead;
        int idx = 0;
        while (mid != null && idx < newLen) {
            idx++;
            mid = mid.next;
        }

        curHead = mergeSort(curHead, newLen);
        mid = mergeSort(mid, len - newLen);
        ListNode pre = curHead, tmp;
        head = curHead;
        while (curHead != null && mid != null) {
            if (curHead.val <= mid.val) {
                pre = curHead;
                curHead = curHead.next;
            } else {
                if (pre == curHead) {
                    head = mid;
                    pre = head;
                } else {
                    pre.next = mid;
                    pre = pre.next;
                }
                tmp = mid.next;
                mid.next = curHead;
                mid = tmp;
            }

        }
        if (mid != null) {
            curHead = pre;
            curHead.next = mid;
        }
        return head;

    }


    private ListNode insertSort(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head.next, insert = null, tmp = null;
        ListNode newHead = head;
        newHead.next = null;
        while (cur != null) {
            insert = newHead;
            while (insert != null) {
                if (insert.val <= cur.val) {
                    if (insert.next == null) {
                        tmp = cur.next;
                        insert.next = cur;
                        cur.next = null;
                        cur = tmp;
                        break;
                    } else {
                        if (insert.next.val > cur.val) {
                            tmp = cur.next;
                            cur.next = insert.next;
                            insert.next = cur;
                            cur = tmp;
                            break;
                        }
                    }
                } else {
                    tmp = cur.next;
                    newHead = cur;
                    cur.next = insert;
                    cur = tmp;
                    break;
                }
                insert = insert.next;
            }

        }
        return newHead;
    }

}