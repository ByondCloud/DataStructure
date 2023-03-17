package com.cloud.d暴力匹配;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/15
 * @Time 5:52
 */
public class Main {
    public static void main(String[] args) {
        String str1 = "我爱我的国我爱我的家国是我的国家是我的家";
        String str2 = "我爱我的家";
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    // 暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1index = 0;
        int s2index = 0;

        while (s1index < s1.length && s2index < s2.length) {
            if (s1[s1index] == s2[s2index]) {
                s1index++;
                s2index++;
            } else {
                s1index = s1index - s2index + 1; // 回溯之后向后移一位
                s2index = 0;
            }
        }

        if (s2index == s2.length) {
            return s1index - s2index;
        } else {
            return -1;
        }

    }

}
