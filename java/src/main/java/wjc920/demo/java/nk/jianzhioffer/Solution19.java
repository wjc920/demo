package wjc920.demo.java.nk.jianzhioffer;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 *
 * @author wjc
 * @date 2019年05月15日 下午05:00:59
 */
public class Solution19 {

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int s = 0, ex = matrix[0].length - 1, ey = matrix.length - 1;
        while (s <= ex && s <= ey) {
            int x = s, y = s;
            if (x == ex) {
                while (y <= ey) {
                    result.add(matrix[y][x]);
                    y++;
                }
                break;
            }
            if (y == ey) {
                while (x <= ex) {
                    result.add(matrix[y][x]);
                    x++;
                }
                break;
            }
            while (x <= ex) {
                result.add(matrix[y][x]);
                x++;
            }
            x--;
            y++;
            while (y <= ey) {
                result.add(matrix[y][x]);
                y++;
            }
            y--;
            x--;
            while (x >= s) {
                result.add(matrix[y][x]);
                x--;
            }
            x++;
            y--;
            while (y > s) {
                result.add(matrix[y][x]);
                y--;
            }
            s += 1;
            ex -= 1;
            ey -= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution19 s = new Solution19();
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int[][] matrix = {{1}, {2}, {3}, {4}, {5}};
        System.out.println(s.printMatrix(matrix));
    }
}
