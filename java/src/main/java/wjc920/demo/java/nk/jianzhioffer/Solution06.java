package wjc920.demo.java.nk.jianzhioffer;

/**
 * 旋转数组的最小数字
 *
 * @author wjc
 * @date 2019年05月05日 下午11:21:46
 */
public class Solution06 {
    public int minNumberInRotateArray(int[] array) {
        int i = 0;
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array[array.length - 1] > array[0]) {
            return array[0];
        }
        for (i = array.length - 1; i > 0; i--) {
            if (array[i] < array[i - 1]) {
                return array[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution06 s = new Solution06();
        int[] array={};
        System.out.println(s.minNumberInRotateArray(array));
    }

}

