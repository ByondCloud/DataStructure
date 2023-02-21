package com.cloud.a递归的原理;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/21
 * @Time 5:40
 */
public class Main {
    public static void main(String[] args) {
        test(4);

    }


    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println(n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }


}
