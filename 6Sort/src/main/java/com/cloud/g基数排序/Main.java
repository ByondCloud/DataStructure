package com.cloud.g基数排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/24
 * @Time 13:20
 */
public class Main {

    public static void main(String[] args) {
//        int arr[] = {53, 3, 543, 748, 14, 214};
        int arr[] = {54, 24, 74, 44, 14, 214};
        radixSort(arr);

    }

    // 基数排序的方法
    public static void radixSort(int[] arr) {

        // 得到数组中最大的位数
        int max = arr[0]; // 假设
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大位数
        int maxLength = (max + "").length();



        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {


            // 第一轮，针对每个元素的个位进行排序

            // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            // 因为没有办法判断每个桶里能塞多少个数，为了防止溢出，只能把桶设计大一些
            // 所以基数排序就是空间换时间的经典算法
            int[][] bucket = new int[10][arr.length];

            // 为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶每次存入的个数，同时也充当了下标的角色
            int[] bucketElementCounts = new int[10];

            // 遍历，把对应的数放入对应的桶中
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                // 放到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 然后让对应的下标++
                bucketElementCounts[digitOfElement]++;
            }
            // 按照桶的顺序，依次放入原来的数组中
            // 指针
            int index = 0;

            // 遍历一遍，并将桶中的数据，放回原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据，我们就放入原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入arr
                        // k 代表第0个桶
                        // l 代表第0个桶的第0个数据
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 每一轮for循环之后，需要把桶中对应的下标清零，下面这句话可以不写
                // 因为在for循环中，会不断创建新的数组
                bucketElementCounts[k] = 0;
            }
            System.out.println(Arrays.toString(arr));
        }






    }

}
