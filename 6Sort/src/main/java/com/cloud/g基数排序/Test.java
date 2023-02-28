package com.cloud.g基数排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 14:36
 */
public class Test {

    public static void main(String[] args) {
        int arr[] = {53, 3, 543, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {

        // 1. 统计最长的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = String.valueOf(max).length();


        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            // 创建桶
            int[][] bucket = new int[10][arr.length];
            // 保存每个桶存储了多少数据【充当下标使用】
            int[] bucketCounts = new int[10];

            // 把对应的数据丢入桶中
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketCounts[digitOfElement]] = arr[j];
                // 下标上移
                bucketCounts[digitOfElement]++;
            }

            // 原数组的指针
            int index = 0;

            // 从桶中把元素放回原数组中
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketCounts[j]; k++) {
                    arr[index] = bucket[j][k];
                    // 下标向前移动
                    index++;
                }

            }
            System.out.println(Arrays.toString(arr));

        }
    }


}
