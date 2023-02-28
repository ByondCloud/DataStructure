package com.cloud.a冒泡排序;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/22
 * @Time 9:46
 */
@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {


    }


    public void BubbleSortReverse() {
        int arr[] = {0, 7, 4, 2, 5 ,1, 9};
        int temp; // 作为交换用的临时变量

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                Arrays.stream(arr).forEach(System.out::print);
                System.out.println();
            }

        }
    }

    public void BubbleSort1() {
        int arr[] = {7, 4, 2, 5 ,1};
        int temp; // 作为交换用的临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public void BubbleSort2() {
        int arr[] = {7, 4, 2, 5 ,1};
        int temp; // 作为交换用的临时变量
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (flag) {
                break;
            }
            for (int j = 0; j < arr.length - 1 - i; j++) {
                flag = true;
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public void BubbleSort3() {
        int arr[] = {7, 4, 2, 5 ,1};
        int temp; // 作为交换用的临时变量
        boolean flag = true; // 如果一次都没有交换，则true
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false; // 进入if发生交换，改为false
                }
            }

            if (flag) {
                break;
            } else {
                flag = true;
            }

        }
        for (int i : arr) {
            System.out.println(i);
        }
    }







}
