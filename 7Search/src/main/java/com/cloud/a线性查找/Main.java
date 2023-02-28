package com.cloud.a线性查找;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 15:28
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = seqSearch(arr, 11);
        if (i == -1) {
            System.out.println("没找到");
        } else {
            System.out.println("下标为" + i);
        }


    }

    public static int seqSearch(int[] arr, int value) {
        // 逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
