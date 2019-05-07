package wjc920.demo.spark.nk.jianzhioffer;

import java.util.LinkedList;
import java.util.List;

/**
 * 重建二叉树
 *
 * @author wjc
 * @date 2019年05月03日 下午11:27:21
 */
public class Solution04 {
    public static void main(String[] args) {
        Solution04 s = new Solution04();
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8}, in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = s.reConstructBinaryTree1(pre, in);
        System.out.println(s.pre(root).toString());
        System.out.println(s.in(root).toString());
    }

    public TreeNode reConstructBinaryTree1(int[] pre, int[] in) {
        return reConstructBinaryTree1(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree1(int[] pre, int preS, int preE, int[] in, int inS, int inE) {
        if (pre == null || in == null) {
            return null;
        }
        if (preS > preE) {
            return null;
        }
        TreeNode r = new TreeNode(pre[preS]);
        if (preS == preE) {
            return r;
        }
        for (int i = 0; i <= preE - preS; i++) {
            if (pre[preS] == in[inS + i]) {
                r.left = reConstructBinaryTree1(pre, preS + 1, preS + i, in, inS, inS + i - 1);
                r.right = reConstructBinaryTree1(pre, preS + i + 1, preE, in, inS + i + 1, inE);
            }
        }
        return r;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) {
            return null;
        }
        TreeNode r = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return r;
        }
        int i = 0;
        for (; i < in.length; i++) {
            if (pre[0] == in[i]) {
                break;
            }
        }
        int[]
                lp = new int[i],
                li = new int[i],
                rp = new int[pre.length - i - 1],
                ri = new int[pre.length - i - 1];
        for (int j = 0; j < pre.length; j++) {
            if (j < i) {
                lp[j] = pre[j + 1];
                li[j] = in[j];
            } else if (j > i) {
                rp[j - i - 1] = pre[j];
                ri[j - i - 1] = in[j];
            }
        }
        r.left = reConstructBinaryTree(lp, li);
        r.right = reConstructBinaryTree(rp, ri);
        return r;
    }


    public List<Integer> pre(TreeNode root) {
        List<Integer> preList = new LinkedList<>();
        pre(root, preList);
        return preList;
    }

    public void pre(TreeNode root, List<Integer> preList) {
        if (root == null) {
            return;
        }
        preList.add(root.val);
        pre(root.left, preList);
        pre(root.right, preList);
    }

    public List<Integer> in(TreeNode root) {
        List<Integer> inList = new LinkedList<>();
        in(root, inList);
        return inList;
    }

    public void in(TreeNode root, List<Integer> inList) {
        if (root == null) {
            return;
        }
        in(root.left, inList);
        inList.add(root.val);
        in(root.right, inList);
    }

}
