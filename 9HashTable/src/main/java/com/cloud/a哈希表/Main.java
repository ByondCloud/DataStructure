package com.cloud.a哈希表;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/27
 * @Time 14:41
 */
public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new Emp(1, "tom"));
        hashTable.add(new Emp(2, "jack"));
        hashTable.add(new Emp(3, "marry"));
        hashTable.add(new Emp(4, "alice"));

        hashTable.list();

        hashTable.findEmpById(6);
    }
}

class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size; // 表示共有多少条链表

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        // 不要忘记初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();

        }
    }

    public void add(Emp emp) {
        // 根据员工的id，得到应该加入到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedLists[empLinkedListNo].add(emp);
    }

    // 遍历方法
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }


    // 散列函数,使用取模法
    public int hashFun(int id) {
        return id % size;
    }

    // 根据驶入的id，查询成员
    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.println("找到");
            System.out.println(emp);
        } else {
            System.out.println("没找到");
        }
    }

}

// 创建EmpLinkedList
class EmpLinkedList {
    // 头指针
    private Emp head;
    // 添加员工
    public void add(Emp emp) {
        // 如果是添加第一个成员
        if (head == null) {
            head = emp;
            return;
        }

        // 如果不是第一个成员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            // 后移，直到最后
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list() {
        if (head == null) {
            // 说明链表为空
            System.out.println("当前链表为空");
            return;
        }
        System.out.println("当前链表的信息为：");
        Emp curEmp = head;
        while (true) {
            System.out.println(curEmp);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("当前链表为空");
            return null;

        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                return curEmp;
            }
            if (curEmp.next == null) {
                System.out.println("没找到");
                return curEmp;
            }
            curEmp = curEmp.next;
        }

    }

}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
