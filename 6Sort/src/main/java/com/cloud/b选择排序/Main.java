package com.cloud.b选择排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/22
 * @Time 11:43
 */
public class Main {
    public static void main(String[] args) {
        int arr[] = {7, 4, 2, 5, 1};
        int[] ints = selectSort(arr);
        Arrays.stream(ints).forEach(System.out::println);
    }

    // 从小到大
    public static int[] selectSort(int[] arr) {


        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;

            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;

                }
            }
            // 如果最小值不是自己，就交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }
        return arr;
    }

}
