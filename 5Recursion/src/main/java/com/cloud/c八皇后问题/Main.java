package com.cloud.c八皇后问题;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/21
 * @Time 11:35
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.check(1);
    }





    // 定义一个max表示一共有多少个皇后
    int max = 5;

    // 定义数组array，保存皇后放置位置的结果，下标为行数
    int[] array = new int[max];

    // 输出皇后摆放的位置输出
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d\t", array[i]);
        }
        System.out.println();
    }

    // 查看当我们放置第n个皇后，检测皇后是否和之前冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 这里运用的就是列不能相等或者行差与列差的值不能相等
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 放置第n个皇后
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        // 依次放入皇后，并查询是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后，放到该行的第一列
            array[n] = i;
            // 如果放该行不冲突，就放下一行
            if (judge(n)) {
                check(n+1);
            }
            // 如果冲突，就继续执行array[n] = i;
        }
    }

}
