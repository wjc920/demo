package wjc920.demo.java.nk.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树的后序遍历序列
 *
 * @author wjc
 * @date 2019年05月21日 上午12:01:43
 */
public class Solution23 {

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        int s = 0, e = sequence.length - 1;
        return check(sequence, s, e);
    }

    private boolean check(int[] seq, int s, int e) {
        if (s >= e) {
            return true;
        }
        boolean gl = false;
        int mid = 0;
        for (int i = s; i <= e - 1; i++) {
            if (seq[i] > seq[e]) {
                if (!gl) {
                    mid = i;
                }
                gl = true;
            }
            if (gl && seq[i] < seq[e]) {
                return false;
            }
        }
        return check(seq, s, mid - 1) && check(seq, mid, e - 1);
    }
}
