package com.cloud.d希尔排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/22
 * @Time 14:43
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort4(arr);
    }


    // 【交换法】不推荐
    public static void shellSort(int[] arr) {
        int temp;
        // 分组，分成总长度的一半，第一次是5组，第二次是2组，最后一次是1组
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            // 遍历每个组
            for (int i = gap; i < arr.length; i++) {
                // 遍历组中的每个成员【就是反着的冒泡排序】
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }


    public static void shellSort2(int[] arr) {
        // 间隔
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            // 从第gap元素，逐个对其所在的组进行插入
            for (int i = gap; i < arr.length; i++) {
                // 保存需要交换的下标
                int j = i;
                // 保存需要交换的数
                int temp = arr[j];
                // 第5位小于第0位
                if (arr[j] < arr[j - gap]) {
                    // 如果第5位小于第0位，则把第5位变成第0位的数字
                    while (j - gap >= 0 && temp < arr[j - gap]) { // 下标没有超过0 同时 arr[5] < arr[0]
                        // 移动位置
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出循环，就代表找到插入的位置
                    arr[j] = temp;
                }
            }
        }
        Arrays.stream(arr).forEach(System.out::print);
    }


    public static void shellSort3(int[] arr) {

        // d是步长
        int d = arr.length / 2;
        while (d >= 1) {
            int k = 0;
            while (k < d) {
                for (int i = d + k; i < arr.length; i += d) {
                    int temp = arr[i];
                    int j;
                    for (j = i - d; j >= 0; j -= d) {
                        if (arr[j] > temp) {
                            arr[j+d] = arr[j];
                        } else {
                            break;
                        }
                    }
                    j += d;
                    arr[j] = temp;
                }
                k++;
            }
            System.out.println("步长为" + d);
            d /= 2; // 步长减半
            Arrays.stream(arr).forEach(System.out::print);
        }

    }





    // 从小到大
    public static void shellSort4(int[] arr) {
        // 分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 遍历所有的组
            for (int i = gap; i < arr.length; i++) {
                // 插入排序
                // 指针【已经排序好的数组】
                int j = i;
                // 需要排序的值
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }
}



