package com.cloud.b二分查找;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 16:24
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 2, 2, 2, 2, 1, 3, 4, 5, 6};
        List<Integer> list = binarySearch(arr, 0, arr.length - 1, 2);
        System.out.println(list);
    }

    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) >> 1;
        int midVal = arr[mid];

        // 如果需要找的数大于中间数
        if (findVal > midVal) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {

            // 如果要找到很多重复的数字，二话不说先拿个list
            ArrayList<Integer> list = new ArrayList<>();

            // 将指针指向最前面的数
            while (mid > 0 && arr[mid] == findVal) {
                mid--;
            }
            mid++; // 因为最后会在前一个

            // 从第一个遍历到最后
            while (mid < arr.length - 1 && arr[mid] == findVal) {
                list.add(mid);
                mid++;
            }

            return list;
        }

    }
}
