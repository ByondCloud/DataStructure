package com.cloud.e快速排序;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/23
 * @Time 15:26
 */
public class Test {
    public static void main(String[] args) {
        int arr[] = {-9, 78, 0, 23, -567, 70};
    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left - right) / 2];
        int temp;

        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }

            while (arr[r] > pivot) {
                r--;
            }

            if (l == r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }

            if (arr[r] == pivot) {
                l++;
            }
        }


        if (l == r) {
            l++;
            r--;
        }


    }

}
