package com.cloud.c动态规划算法之01背包;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/14
 * @Time 23:07
 */
public class Main {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3}; // 记录物品的重量
        int[] price = {1500, 3000, 2000}; // 记录物品的价值
        int capacityTotal = 8; // 背包的容量
        int itemTotal = weight.length;

        // 创建一个二维数组
        int[][] list = new int[itemTotal + 1][capacityTotal + 1];
        /**
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         */

        for (int i = 0; i < list.length; i++) {
            list[i][0] = 0;
        }
        for (int i = 0; i < list[0].length; i++) {
            list[0][i] = 0;
        }

        // 动态规划
        for (int item = 1; item < list.length; item++) {
            for (int capacity = 1; capacity < list[0].length; capacity++) {
                // 如果所选的物品重量 > 背包总容量
                if (weight[item - 1] > capacity) {
                    // 将上一组数据抄到下面
                    // 例如[0][1] = 1;
                    // 那么现在求[1][1] = [0][1] = 1
                    list[item][capacity] = list[item - 1][capacity];
                } else {
                    //                                                         当前商品的价格   + 剩余空间的最大值 list[当前产品][背包重量 - 当前产品重量 = 剩余重量]
                    list[item][capacity] = Math.max(list[item - 1][capacity], price[item - 1] + list[item - 1][capacity - weight[item - 1]]);


                }
            }
        }



        for (int i = 0; i < list.length; i++) {
            System.out.println(Arrays.toString(list[i]));
        }
    }
}
