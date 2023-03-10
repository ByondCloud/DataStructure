package com.cloud.b顺序存储二叉树;

import java.util.ArrayList;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/1
 * @Time 16:07
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);

    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空，就不遍历了
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        // 下面是前序遍历
        System.out.println(arr[index]);
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

}
