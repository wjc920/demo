package wjc920.demo.java.nk.jianzhioffer;

/**
 * 链表中倒数第k个结点
 *
 * @author wjc
 * @date 2019年05月15日 下午05:00:59
 */
public class Solution14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode cur = head, target = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
            if (len > k) {
                target = target.next;
            }
        }
        if (len < k) {
            return null;
        } else {
            return target;
        }
    }

    public static void main(String[] args) {
        Solution14 s = new Solution14();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println(s.FindKthToTail(head, 4).val);
    }
}
