package com.cloud.a数组模拟队列;

import java.util.Scanner;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/8
 * @Time 15:48
 */
public class a队列 {
    public static void main(String[] args) {
        // 创建6位的队列
        ArrayQueue arrayQueue = new ArrayQueue(6);

        // 往队列放数据
        arrayQueue.addQueue(100);
        arrayQueue.addQueue(200);
        arrayQueue.addQueue(300);
        arrayQueue.addQueue(400);

        // 查看当前front和rear所在的位置
        System.out.println("front = " + arrayQueue.getFront());
        System.out.println("rear = " + arrayQueue.getRear());

        System.out.println();
        // 当我取出一个数据，再次查看front的位置
        int queue = arrayQueue.getQueue();
        System.out.println("取出数据：" + queue);
        System.out.println("front = " + arrayQueue.getFront());
        System.out.println("rear = " + arrayQueue.getRear());

        System.out.println();
        // 再取出一个数据，再次查看front的位置
        int queue2 = arrayQueue.getQueue();
        System.out.println("取出数据：" + queue2);
        System.out.println("front = " + arrayQueue.getFront());
        System.out.println("rear = " + arrayQueue.getRear());

    }
}

class ArrayQueue {
    private int maxSize; // 表示队列的最大容量
    private int front; // 指向队列头
    private int rear; // 队列尾
    private int arr[]; // 存放的数据

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部的前一个位置
        rear = -1; // 指向队列尾部最后一个数据
    }
    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满了");
            return;
        }
        rear++; // 让rear后移
        arr[rear] = n;
    }

    // 从队列中取出数据
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 显示头数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }



}
