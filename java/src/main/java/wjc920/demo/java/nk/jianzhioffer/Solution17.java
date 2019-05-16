package wjc920.demo.java.nk.jianzhioffer;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 树的子结构
 *
 * @author wjc
 * @date 2019年05月15日 下午05:00:59
 */
public class Solution17 {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return false;
        }
        List<TreeNode> roots = getRoots(root1, root2);
        for (TreeNode t : roots) {
            if (check(t, root2)) {
                return true;
            }
        }
        return false;
    }

    public boolean HasSubtree1(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return false;
        }
        if (root1 == null) {
            return false;
        }
        boolean flag = false;
        if (root1.val == root2.val) {
            flag = check(root1, root2);
        }
        if (flag) return flag;
        flag = check(root1.left, root2);
        if (flag) return flag;
        flag = check(root1.right, root2);
        return flag;
    }

    public boolean check(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null || root1.val != root2.val) {
            return false;
        }
        return check(root1.left, root2.left) && check(root1.right, root2.right);
    }

    public List<TreeNode> getRoots(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stk = new Stack<>();
        TreeNode cur = root1;
        List<TreeNode> roots = new LinkedList<>();
        do {
            while (cur != null) {
                stk.push(cur);
                if (cur.val == root2.val) {
                    roots.add(cur);
                }
                cur = cur.left;
            }
            while (!stk.isEmpty()) {
                cur = stk.pop();
                cur = cur.right;
                if (cur != null) {
                    stk.push(cur);
                    break;
                }
            }
        } while (!stk.isEmpty());
        return roots;
    }

    public static void main(String[] args) {
        Solution17 s = new Solution17();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(5);
        System.out.println(s.HasSubtree1(root1, root2));
    }
}
