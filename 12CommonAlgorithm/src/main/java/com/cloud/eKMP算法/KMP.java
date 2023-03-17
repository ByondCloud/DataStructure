package com.cloud.eKMP算法;


import java.util.Arrays;

public class KMP {
    public static int[] kmpNext(String pattern) {
        int[] next = new int[pattern.length() + 1];
        int i = 1, j = 0;
        while (i < pattern.length()) {
            if (j == 0 || pattern.charAt(i) == pattern.charAt(j))
                next[++i] = ++j;
            else
                j = next[j];
        }

        return next;
    }

    public static int search(String text, String pattern) {
        int[] next = kmpNext(pattern);
        System.out.println(Arrays.toString(next));
        int i = 0, j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = next[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABC";
        int index = search(text, pattern);
        if (index == -1) {
            System.out.println("Pattern not found in the text.");
        } else {
            System.out.println("Pattern found at index " + index);
        }
    }
}
