package com.cloud.d中缀表达式转后缀表达式;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/20
 * @Time 5:48
 */
public class Main {
    public static void main(String[] args) {
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList2(suffixExpression);
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println(strings1);
    }

    public static List<String> toInfixExpressionList(String s) {
        // 定义list，存放中缀表达式
        List<String> list = new ArrayList<>();
        int i = 0; // 指针，用来遍历中缀表达式字符串
        String str; // 对多位数的拼接
        char c;

        do {
            // 如果c是一个非数字，那么就需要加入list中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(String.valueOf(c));
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }


    public static List<String> toInfixExpressionList2(String s) {
        // 定义list，存放中缀表达式
        List<String> list = new ArrayList<>();

        Pattern compile = Pattern.compile("\\d+|\\W");
        Matcher matcher = compile.matcher(s);
        while (matcher.find()) {
            list.add(matcher.group(0));
        }
        return list;

    }

    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();
        // 因为s2实际没有弹栈的操作，我们就直接用arraylist
        List<String> s2 = new ArrayList<>();

        for (String s : list) {
            // 如果是数字，就添加到s2
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 去掉括号(
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                // 将s1的东西放入到s2中
                s1.push(s);
            }
        }

        // 将s1剩余的运算符依次压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;

    }


}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }

}
