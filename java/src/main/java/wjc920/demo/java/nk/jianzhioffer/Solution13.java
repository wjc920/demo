package wjc920.demo.java.nk.jianzhioffer;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * @author wjc
 * @date 2019年05月09日 下午11:19:41
 */
public class Solution13 {
    public void reOrderArray(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                for (int j = i; j > 0; j--) {
                    if (array[j - 1] % 2 == 1) {
                        break;
                    }
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution13 s = new Solution13();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        s.reOrderArray(arr);
        System.out.println();
    }
}
