package com.cloud.b计算器;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/19
 * @Time 23:45
 */
public class Main {
    public static void main(String[] args) {
        String expression = "30+2*6-2"; // 13
        NumStack numStack = new NumStack(10);
        SymbolStack symbolStack = new SymbolStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int symbol = 0;
        int result = 0;
        char ch = ' ';

        // 如果是多位数，需要用于拼接
        String str = "";

        while (true) {

            ch = expression.charAt(index);
            // 判断ch是什么，如果是符号就判断优先级，不是符号就入数字栈
            if (symbolStack.isSymbol(ch)) {
                if (!symbolStack.isEmpty()) {
                    // 判断当前的符号优先级是否小于符号栈顶的符号
                    if (symbolStack.priority(ch) <= symbolStack.priority(symbolStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        symbol = symbolStack.pop();
                        result = symbolStack.cal(num1, num2, symbol);
                        // 把运算的结果和当前的符号入栈
                        numStack.push(result);
                        symbolStack.push(ch);
                    } else {
                        symbolStack.push(ch);
                    }
                } else {
                    symbolStack.push(ch);
                }

            } else {

                str += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(str));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，继续扫描，如果不是，入栈
                    if (symbolStack.isSymbol(expression.charAt(index+1))) {
                        numStack.push(Integer.parseInt(str));
                        str = "";
                    }
                }

            }
            index++;
            // 判断是否扫描完成
            if (index >= expression.length()) {
                break;
            }
        }
        // 最后留在栈里的数字就是结算结果
        while (true) {
            if (symbolStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            symbol = symbolStack.pop();
            result = symbolStack.cal(num1, num2, symbol);
            // 把运算的结果和当前的符号入栈
            numStack.push(result);
        }
        result = numStack.pop();
        System.out.println("结果为:" + result);
    }

}


class NumStack {
    public int top = -1;
    public int[] stack;
    public int maxSize;


    public NumStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
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
            throw new RuntimeException("空了");
        }
        int value = stack[top];
        top--;
        return value;
    }
}

class SymbolStack {
    public int top = -1;
    public int[] stack;
    public int maxSize;


    public SymbolStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
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
            throw new RuntimeException("空了");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 判断符号优先级
    public int priority(int symbol) {
        if (symbol == '*' || symbol == '/') {
            return 1;
        }
        if (symbol == '+' || symbol == '-') {
            return 0;
        }
        return -1;
    }

    // 判断是否为运算符
    public boolean isSymbol(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int symbol) {
        int result = 0;
        switch (symbol) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    // 查看当前栈顶的值
    public int peek() {
        return stack[top];
    }
}
