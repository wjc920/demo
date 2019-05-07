package wjc920.demo.spark.nk.jianzhioffer;

/**
 * 二进制中1的个数
 *
 * @author wjc
 * @date 2019年05月07日 上午12:10:56
 */
public class Solution11 {
    public int NumberOf1(int n) {
        int result = 0;
        int tmp = n;
        for (int i = 0; i < 32; i++) {
            tmp = n >> i;
            if ((tmp & 1) == 1) {
                result++;
            }
            System.out.println(tmp & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution11 s = new Solution11();
        System.out.println(s.NumberOf1(-7));
    }
}
