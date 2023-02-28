package com.cloud.e快速排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/23
 * @Time 14:51
 */
public class Main {
    public static void main(String[] args) {
//        int arr[] = {-9, 78, 0, 23, -567, 70};
        int arr[] = {0,1,2,7,3,4};
        quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    // 这个写法有点小问题，如果遇到{0,1,2,3,4}这样的，就会越界，待解决
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        int pivot = arr[right]; // 中轴值
        int temp = 0; // 临时变量 ，用来做交换
        while (l < r) { // 让比中轴值小的放到左边，比中轴值大的数放到右边

            while (arr[l] < pivot) { // 在pivot左边一直找，找到大于等于pivot值才退出
                l++;
            }

            while (arr[r] > pivot) { // 在pivot左边一直找，找到小于等于pivot值才退出
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现arr[l] = pivot值相等
            // 让右指针往前走一步
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现arr[r] = pivot值相等
            // 让左指针往前走一步
            if (arr[r] == pivot) {
                 l++;
            }
        }

        // 如果l == r，同时不能在边缘，那么必须l++，r--，否则会出现栈溢出
        if ((l != left && l == r) || (l != right && l == r)) {
            l++;
            r--;
        }

        // 向左递归
        // 这里注意，是left和r，因为上面的if判断，已经让r--了
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > r) {
            quickSort(arr, l, right);
        }
    }

}
