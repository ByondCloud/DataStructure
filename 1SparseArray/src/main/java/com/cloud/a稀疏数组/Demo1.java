package com.cloud.a稀疏数组;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/8
 * @Time 12:22
 */
public class Demo1 {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        // 1表示黑子 2表示白子
        array[1][2] = 1;
        array[2][3] = 2;

//        输出查看
//        for (int[] ints : array) {
//            for (int anInt : ints) {
//                System.out.printf("%d\t", anInt);
//            }
//            System.out.println();
//        }


// 将二维数组转换为稀疏数组
        int sum = 0;
        // 1. 遍历整个二维数组，看有多少个有效数据
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
            }
        }
        // 2. 创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        // 3. 给稀疏数组赋值
        // 二维数组.length = 二维数组的总行数
        // 二维数组[行号].length = 该行有多少个元素【就是列数】
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;

        // 4. 遍历二维数组，将非0的值放入稀疏数组中
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        // 5. 输出稀疏数组
//        for (int[] ints : sparseArray) {
//            for (int anInt : ints) {
//                System.out.printf("%d\t", anInt);
//            }
//            System.out.println();
//        }

// 将稀疏数组转换为二维数组
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int data;
        int array2[][] = new int[row][col];
        for (int i = 1; i < sparseArray.length; i++) {
            row = sparseArray[i][0];
            col = sparseArray[i][1];
            data = sparseArray[i][2];
            array2[row][col] = data;
        }




    }
}
