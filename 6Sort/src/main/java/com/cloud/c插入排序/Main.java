package com.cloud.c插入排序;

import java.util.Arrays;
import java.util.Date;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/22
 * @Time 12:49
 */
public class Main {
    public static void main(String[] args) {
        int arr[] = {7, 4, 2, 5, 1};
        long start = System.currentTimeMillis();
        int[] ints = insertSort(arr);
        Arrays.stream(ints).forEach(System.out::print);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    // 从小到大
    public static int[] insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i]; // 指需要排序的数
            int insertIndex = i - 1; // 指排好序的最后一位数

            // insertIndex >= 0 用来防止数组越界
            // 如果需要排序的数 < 排好序的数，那么就意味着位置不对，现在的位置上的数 = 前一个数
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
                Arrays.stream(arr).forEach(System.out::print);
                System.out.println();
            }

            // 如果位置没有变过，则不需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

        }
        return arr;

    }

}
