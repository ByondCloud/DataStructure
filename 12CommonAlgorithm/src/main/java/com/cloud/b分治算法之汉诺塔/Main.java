package com.cloud.b分治算法之汉诺塔;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/14
 * @Time 22:08
 */
public class Main {
    public static void main(String[] args) {
        hanoiTower(2, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔的移动方案
     * 使用分治算法解决
     * @param num 表示有多少个盘
     * @param a 柱子A
     * @param b 柱子B
     * @param c 柱子C
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
            // a到b，中间使用到了c
            hanoiTower(num - 1, a, c, b);
            System.out.println(a + "->" + c);
            // b到c，中间使用到了a
            hanoiTower(num - 1, b, a, c);
        }
    }

}



