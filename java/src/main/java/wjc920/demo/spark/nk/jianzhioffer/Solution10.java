package wjc920.demo.spark.nk.jianzhioffer;

public class Solution10 {
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        return RectCover(target - 1) + RectCover(target - 2);
    }

    public static void main(String[] args) {
        Solution10 s = new Solution10();
        System.out.println(s.RectCover(4));
    }
}
