package wjc920.demo.spark.nk.jianzhioffer;

/**
 * 变态跳台阶
 *
 * @author wjc
 * @date 2019年05月06日 下午11:21:46
 */
public class Solution09 {
    public int JumpFloorII(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        int result = 0;
        for (int i = 1; i <= target; i++) {
            result += JumpFloorII(target - i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution09 s = new Solution09();
        System.out.println(s.JumpFloorII(3));
    }
}
