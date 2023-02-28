package com.cloud.d斐波那契;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/26
 * @Time 10:18
 */
public class Main {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 100};
        System.out.println(fibSearch(arr, 1234));
    }

    // 获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


    /**
     *
     * @param a     数组
     * @param key   我们需要查找的数
     * @return      返回对应的下标
     */
    // 斐波那契查找算法【非递归】
    // 主要就是斐波那契数列对应的是数组的个数，例如a.length - 1 = 5，那么要求斐波那契数列[1,1,2,3,5]
    // 【有bug】
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示斐波那契的下标
        int mid = 0; // 存放mid值
        int f[] = fib(); //  获取到斐波那契数列
        // 这一步主要是新建的数组要等于斐波那契数列的数字
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k] 可能大于a的长度，所以需要构造一个新的数组，并指向a
        int[] temp = Arrays.copyOf(a, f[k]);
        // 这一步是假设是[1,3,5,0,0,0]，呢么就是把后面多出来的0变成最后一位数，就是[1,3,5,5,5,5]
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            // 确定可能的下标
            mid = low + f[k - 1] - 1;
            // 如果大于要找的数
            if (key < temp[mid]) {
                // 类似折半查找法了
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }


}
