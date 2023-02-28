package com.cloud.f归并排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 10:43
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length]; // 归并排序需要额外的一个数组
        mergeSort(arr, 0, arr.length - 1, temp);

//        System.out.println("归并排序后");
//        System.out.println(Arrays.toString(arr));

    }



    // 分治算法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }



    /**
     * 合并的方法
     * @param arr 需要排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 左边有序序列的初始索引
        int j = mid + 1; // 初始化j，右边有序序列的初始索引
        int t = 0; // 中转数组的指针

        // 一、
        // 先把左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕
        while (i <= mid && j <= right) {
            // 如果左边的有序序列的当前元素   小于等于    右边的有序序列的当前元素
            // 那么将 左边的有序序列的当期元素拷贝到 temp数组
            // 然后左边的数组指针向后移，temp指针向后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                // 反之
                temp[t] = arr[j];
                t++;
                j++;
            }
        }


        // 二、
        // 把有剩余数据的一边的数据依次填充到temp

        // 如果左边的有序序列还有剩余
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        // 如果右边的有序序列还有剩余
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }


        // 三、
        // 将temp数组的所有元素填充到arr

        // 并不是每次都拷贝所有

        // 指针归零
        t = 0;
        // tempLeft是temp数组左边的指针
        // 假设现在需要拷贝的是右边的数组，那么left就不是0
        int tempLeft = left;
        // 同样的，假如需要拷贝的是左边的数组，那么right != arr.length - 1
        // 这里的while循环就是为了拷贝对应的数组到temp里面去
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

        System.out.println(Arrays.toString(arr));


    }

}
