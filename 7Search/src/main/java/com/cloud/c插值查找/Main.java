package com.cloud.c插值查找;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 17:48
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 100, 120, 200, 300, 310, 500};
        int i = insertValueSearch(arr, 0, arr.length - 1, 200);
        System.out.println(i);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //  mid = left + 大三角形的下边   /  大三角形的上边            *     小三角形的上边
        int mid = left + (right - left) / (arr[right] - arr[left]) * (findVal - arr[left]);

        if (findVal < arr[mid]) {
            // 向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            // 向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }


    }
}
