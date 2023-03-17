package com.cloud.a二分查找非递归;

/** 二分查找非递归
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/14
 * @Time 21:32
 */
public class NoRecur {
    public static void main(String[] args) {
        int[] arr = {1,5,7,9,11,12,13,15,19,20};
        int i = binarySearch(arr, 19);
        System.out.println(i);
    }

    /**
     *
     * @param arr 待查找的数组
     * @param target 需要找的数
     * @return 返回下标，没找到-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}
