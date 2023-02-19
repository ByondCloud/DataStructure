package com.cloud.c约瑟夫问题;

import jdk.jfr.DataAmount;
import lombok.Data;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/19
 * @Time 21:30
 */
public class Main {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);

    }
}


@Data
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点

    public Boy(int no) {
        this.no = no;
    }
}

class CircleSingleLinkedList {
    // 创建一个first节点
    private Boy first = null;

    // 添加小孩，构建环形链表
    public void addBoy(int nums) {
        // nums 做数据校验
        if (nums < 1) {
            System.out.println("编号不正确");
            return;
        }

        // 辅助指针，指向创建的前一个对象
        Boy curBoy = null;
        // 创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩，将自己和自己形成闭环
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else { // 如果不是第一个小孩，那么
                // 先将前一个节点的next设置为新加的节点
                curBoy.setNext(boy);
                // 新加的节点的下一个设置为first
                boy.setNext(first);
                // 指针下移
                curBoy = boy;
            }
        }
    }

    // 遍历
    public void showBoy() {
        if (first == null) {
            System.out.println("空");
            return;
        }
        // 辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy.getNo());
            if (curBoy.getNext() == first) {
                System.out.println("输出完毕");
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    // 顺序出圈
    public void countBoy(int startNo, int countNum, int nums) {
        // 先校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("数据有误");
            return;
        }

        // 辅助指针，让他指向最后一个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        // 在小孩报数之前，先让first指向开始报数的节点，helper指向first后一个节点
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 小孩开始报数，first和helper需要同时移动
        while (true) {
            if (helper == first) {
                break;
            }
            // 让first和helper指针同时移动countNum【因为比如数2，自己已经数1了，实际只移动了一次】
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo());
            // 将小孩踢出去
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后一个节点" + first.getNo());


    }


}

