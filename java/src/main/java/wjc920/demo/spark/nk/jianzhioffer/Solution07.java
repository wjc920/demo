package wjc920.demo.spark.nk.jianzhioffer;

/**
 * 用两个栈实现队列
 *
 * @author wjc
 * @date 2019年05月03日 下午11:31:25
 */
public class Solution07 {
    public int Fibonacci(int n) {
        int[] a = {0, 1, 1};
        if (n <= 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        for (int i = 3; i <= n; i++) {
            a[i % a.length] = a[(i + 1) % a.length] + a[(i + 2) % a.length];
        }
        return a[n % a.length];
    }

    public static void main(String[] args) {
        Solution07 s = new Solution07();
        for (int i = 0; i < 10; i++) {
            System.out.println(s.Fibonacci(i));
        }
    }

}

