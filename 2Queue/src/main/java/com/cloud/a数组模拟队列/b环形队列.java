package com.cloud.a数组模拟队列;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/9
 * @Time 17:04
 */
public class b环形队列 {
    public static void main(String[] args) {
        // 创建6位的队列
        CircleQueue circleQueue = new CircleQueue(4);

        // 往队列放数据
        circleQueue.addQueue(100);
        circleQueue.addQueue(200);
        circleQueue.addQueue(300);
        circleQueue.addQueue(400);

        // 读取2个数据
        circleQueue.getQueue();
        circleQueue.getQueue();

        // 查看front位置
        System.out.println(circleQueue.getFront());

        // 然后我们再写一个数据进去
        circleQueue.addQueue(500);

        // 查看我们的rear跑到哪里去了
        System.out.println(circleQueue.getRear());
        circleQueue.showQueue();

    }
}

class CircleQueue {
    private int maxSize; // 表示队列的最大容量
    private int front; // 指向队列头
    private int rear; // 队列尾 + 1
    private int arr[]; // 存放的数据
    private boolean noNull; // 有数据就是true

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0; // 指向队列头部
        rear = 0; // 指向队列尾部 + 1
    }
    // 判断队列是否满
    public boolean isFull() {
        if (noNull) {
            return size() == maxSize;
        }
        return false;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        if (noNull) {
            return false;
        }
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("队列满了");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
        noNull = true;
    }

    // 从队列中取出数据
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        noNull = false;
        return temp;
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }




}
