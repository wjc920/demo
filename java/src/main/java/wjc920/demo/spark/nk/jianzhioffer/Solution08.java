package wjc920.demo.spark.nk.jianzhioffer;

/**
 * 跳台阶
 *
 * @author wjc
 * @date 2019年05月06日 下午11:21:21
 */
public class Solution08 {
    public int JumpFloor(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    public static void main(String[] args) {
        Solution08 s = new Solution08();
        System.out.println(s.JumpFloor(9));
    }
}
