package com.cloud.h堆排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/1
 * @Time 19:56
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 11, 15, 20};
        heapSort(arr);
    }


    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        int temp = 0;


        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        System.out.println();
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * 举例：
     * 第一次i = 1 调整 {4, 6, 8, 5, 9} => {4, 9, 8, 5, 6}
     * 第二次i = 0 调整 {4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     *
     * @param arr       待调整的数组
     * @param i         非叶子节点所在数组的索引
     * @param length    对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 取出当前元素的值，保存在临时变量中
        int temp = arr[i];

        // j代表i节点的左子节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            // 这里先比较i节点下的左右子节点哪个最大，得出结果后再和i节点比较是否要交换
            if (j + 1 < length && arr[j] < arr[j + 1]) { // 左h子节点的值小于右子节点的值
                j++; // j指向右子节点
            }
            if (arr[j] > temp) { // 如果子节点大于父节点
                arr[i] = arr[j]; // 把较大的值赋值给当前节点
                i = j; // 继续循环比较
            } else {
                break;
            }
        }
        // 当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶堆的位置
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));
    }

}
