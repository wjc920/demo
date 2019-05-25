package wjc920.demo.java.nk.jianzhioffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 *
 * @author wjc
 * @date 2019年05月25日 下午10:20:31
 */
public class Solution24 {

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        traverse(root, new ArrayList<>(), target);
        return result;
    }

    public void traverse(TreeNode root, ArrayList<TreeNode> t, int target) {
        if (root == null) {
            return;
        }
        target -= root.val;
        t.add(root);
        if (root.left == null && root.right == null && target == 0) {
            ArrayList<Integer> tVal = new ArrayList<>();
            for (TreeNode tmp : t) {
                tVal.add(tmp.val);
            }
            result.add(tVal);
        } else {
            traverse(root.left, t, target);
            traverse(root.right, t, target);
        }
        target += root.val;
        t.remove(root);
    }
}
