package com.cloud.a数组模拟栈;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/19
 * @Time 22:57
 */
public class Main {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());




    }
}

class ArrayStack {
    private int MaxSize; // 栈的大小
    private int[] stack; // 数组，具体存放的地方
    private int top = -1; // 栈顶


    public ArrayStack(int maxSize) {
        MaxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == MaxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }


    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            System.out.println("空");
            throw new RuntimeException("空");
        }
        int value = stack[top];
        top--;
        return value;
    }


    // 遍历，从栈顶到栈底
    public void list() {
        if (isEmpty()) {
            System.out.println("空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // 遍历，从栈底到栈顶
    public void listReverse() {
        if (isEmpty()) {
            System.out.println("空");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i]);
        }
    }


}

