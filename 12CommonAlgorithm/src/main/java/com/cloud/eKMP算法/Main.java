package com.cloud.eKMP算法;

import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/15
 * @Time 23:33
 */
public class Main {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABABAAABABAA";

        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));

//        int index = kmpSearch(str1, str2, next);
//        System.out.println(index);

    }


    // KMP算法
    public static int kmpSearch(String str1, String str2, int[] next) {

        // 遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {

            // 考虑不等
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }


    // 获取到一个字符串 的部分匹配值
    public static int[] kmpNext(String dest) {
        // 用于存储匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            // 如果不等
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                // 部分匹配值 +1
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
