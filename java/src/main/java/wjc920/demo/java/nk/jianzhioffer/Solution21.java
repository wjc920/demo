package wjc920.demo.java.nk.jianzhioffer;

/**
 * 栈的压入、弹出序列
 *
 * @author wjc
 * @date 2019年05月21日 上午12:01:43
 */
public class Solution21 {

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int[] stat = new int[pushA.length];
        int max = 0;
        int ind = 0;
        for (int i = 0; i < popA.length; i++) {
            int tmp = popA[i];
            boolean exist = false;
            if (tmp > max) {
                max = tmp;
                for (int j = 0; j < stat.length; j++) {
                    if (pushA[j] == tmp) {
                        stat[j] = 1;
                        ind = j;
                        exist = true;
                    }
                }
                if (!exist) {
                    return false;
                }
            } else {
                for (int j = ind; j >= 0; j--) {
                    if (stat[j] == 0) {
                        if (pushA[j] != tmp) {
                            return false;
                        } else {
                            stat[j] = 1;
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] pushA = new int[] { 1, 2, 3, 4, 5 };
        int[] popA = new int[] { 4, 3, 5, 1, 2 };
        System.out.println(new Solution21().IsPopOrder(pushA, popA));
    }
}
