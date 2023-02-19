package com.cloud.a单向链表;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/18
 * @Time 21:11
 */
public class Main {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList.reverse();
        singleLinkedList.list();

    }
}

// 定义节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

// 定义LinkedList
class SingleLinkedList {
    // 初始化头节点，头结点不动，不存放任何具体数据
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 不考虑顺序的时候，找到最后的节点，并将最后的节点中的next指向新的节点
    public void add(HeroNode heroNode) {
        // head节点不能动，需要一个辅助节点
        HeroNode temp = head;
        // 遍历链表 找到最后
        while (true) {
            // 当temp.next为空，就找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果不为空，temp后移
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    // 插入
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; // 查询添加的编号是否存在，如果存在就不能添加
        while (true) {
            if (temp.next == null) { // 说明到最后了
                break;
            }
            if (temp.next.no > heroNode.no) { // 找到位置，就在temp的后面插入
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true; // 编号存在
                break;
            }
            temp = temp.next; // 后移
        }

        // 退出循环后判断flag的值
        if (flag) {
            System.out.println("准备插入的编号已经存在");
        } else {
            // 插入
            // 假如现在插入2 ，原本是1 3 4
            // 那么temp指向的就是1，heroNode就是1
            // 我们先让2的下一个 = 1的下一个就是3【2的下一个 = 3】
            heroNode.next = temp.next;

            // 然后把1的下一个 = 需要插入的节点【1.next = 2】
            temp.next = heroNode;
        }

    }

    // 修改，但是不能改no
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        // 是否找到该节点
        while (true) {
            // 如果为空，遍历整个链表了
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag来判断是否找到
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("妹找到");
        }
    }

    // 删除节点
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                // 找到删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没找到需要删除的节点");
        }

    }



    // 显示链表
    public void list() {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动，所以还是需要一个辅助变量
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 后移temp
            temp = temp.next;
        }
    }

    // 一共有多少个有效节点
    // 重点：temp需要指向第一个有效节点，而不是head
    public int getLength() {
        if (head.next == null) {
            return 0;
        }
        int num = 0;
        HeroNode temp = head.next;
        while (true) {
            if (temp != null) {
                num++;
                temp = temp.next;
            }
        }
    }

    // 查找倒数第k个节点
    // 先遍历看一共有几个节点，然后就fori遍历过去就是了
    public HeroNode getNode(int k) {
        int index = getLength();

        HeroNode temp = head.next;
        for (int i = 0; i < index - k; i++) {
            temp = temp.next;
        }
        return temp;

    }

    // 链表反转
    public void reverse() {
        // 代码健壮性就不写了

        // 指针指向第一个有效节点
        HeroNode temp = head.next;
        // 保存下一个节点
        HeroNode next = null;
        // 新的头
        HeroNode reverseHead = new HeroNode(0, "", "");

        while (temp != null) {
            // 保存指针指向的下一个节点数据
            next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        // 将head.next 指向 reverseHead.next
        head.next = reverseHead.next;

    }


}
