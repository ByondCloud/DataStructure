package com.cloud.c逆波兰表达式计算器;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/20
 * @Time 1:44
 */
public class Main {
    public static void main(String[] args) {
        // 定义
        String suffixExpression = "3 4 + 5 * 6 -";

        // 1. 上面的字符串放到Arraylist中
        // 2. arraylist传递给方法，配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int res = calculate(list);
        System.out.println(res);
    }

    // 将逆波兰表达式依次放入Arraylist
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    // 计算
    public static int calculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            // 正则表达式取出数，如果是数字，则入栈
            if (s.matches("\\d+")) {
                stack.push(s);
            } else { // 如果不是数字，2个数出栈计算
                // pop2个数，并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (s.equals("+"))
                    result = num1 + num2;
                if (s.equals("-"))
                    result = num1 - num2;
                if (s.equals("*"))
                    result = num1 * num2;
                if (s.equals("/"))
                    result = num1 / num2;
                stack.push(String.valueOf(result));
            }
        }
        // 最后留在stack中的数据就是结果
        return Integer.parseInt(stack.pop());
    }


}
