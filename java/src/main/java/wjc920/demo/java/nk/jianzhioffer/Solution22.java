package wjc920.demo.java.nk.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 上往下打印二叉树
 *
 * @author wjc
 * @date 2019年05月21日 上午12:01:43
 */
public class Solution22 {

    public void fun(Queue<TreeNode> q, ArrayList<Integer> list) {
        if (q==null||q.size() == 0) {
            return;
        }
        TreeNode t = q.poll();
        list.add(t.val);
        if (t.left != null) {
            q.add(t.left);
        }
        if (t.right != null) {
            q.add(t.right);
        }
        fun(q, list);
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null){
            return list;
        }
        q.add(root);
        fun(q, list);
        return list;
    }
}
