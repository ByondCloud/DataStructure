package com.cloud.a稀疏数组;

import java.io.*;
import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/8
 * @Time 13:48
 */
public class HomeWork {
    public static void main(String[] args) throws IOException {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;

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


        String filePath = "f:\\array.txt";

        // 将数组写入f盘
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        StringBuffer stringBuffer = new StringBuffer();
        String info = sparseArray.length + "," + sparseArray[0].length;
        bufferedWriter.write(info);
        bufferedWriter.newLine();
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                stringBuffer.append(anInt).append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.newLine();
            stringBuffer = new StringBuffer();
        }
        bufferedWriter.close();


        // 从f盘读取
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String s = bufferedReader.readLine();
        String[] split = s.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        int[][] sparseArray2 = new int[row][col];
        for (int i = 0; i < row; i++) {
            String[] split1 = bufferedReader.readLine().split(",");
            for (int j = 0; j < col; j++) {
                sparseArray2[i][j] = Integer.parseInt(split1[j]);
            }
        }
        bufferedReader.close();

        // 将稀疏数组转换为二维数组
        row = sparseArray[0][0];
        col = sparseArray[0][1];
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
