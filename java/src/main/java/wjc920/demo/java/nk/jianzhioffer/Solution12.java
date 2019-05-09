package wjc920.demo.java.nk.jianzhioffer;

/**
 * 数值的整数次方
 *
 * @author wjc
 * @date 2019年05月09日 下午11:19:41
 */
public class Solution12 {
    public double Power(double base, int exponent) {
        double result = 1.0;
        int a = Math.abs(exponent);
        while (a-- > 0) {
            result *= base;
        }
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution12 s = new Solution12();
        System.out.println(s.Power(2, -2));
    }
}
