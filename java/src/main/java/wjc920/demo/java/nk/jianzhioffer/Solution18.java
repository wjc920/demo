package wjc920.demo.java.nk.jianzhioffer;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的镜像
 *
 * @author wjc
 * @date 2019年05月15日 下午05:00:59
 */
public class Solution18 {

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public static void main(String[] args) {
        Solution18 s = new Solution18();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        s.Mirror(root1);
        System.out.println();
    }
}
