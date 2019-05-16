package wjc920.demo.java.nk.jianzhioffer;

/**
 * 反转链表
 *
 * @author wjc
 * @date 2019年05月15日 下午05:00:59
 */
public class Solution15 {
    public ListNode ReverseList1(ListNode head) {
        ListNode cur = head, newHead = head, tmp = null;
        while (cur != null) {
            tmp = cur;
            cur = cur.next;
            tmp.next = newHead;
            if (newHead == head) {
                newHead.next = null;
            }
            newHead = tmp;
        }
        return newHead;
    }
    public ListNode ReverseList(ListNode head) {
        ListNode cur=head;
        ListNode next=null;
        ListNode pre=null;
        if(head==null||head.next==null){
            return head;
        }
        while(cur!=null){
            next=cur.next;
            cur.next=pre;

            pre=cur;
            cur=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Solution15 s = new Solution15();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head = s.ReverseList(head);
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
