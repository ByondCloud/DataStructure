package com.cloud.c线索化二叉树;

import lombok.Data;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/1
 * @Time 17:01
 */
public class Main {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node6 = new HeroNode(6, "吴用");
        HeroNode node8 = new HeroNode(8, "公孙胜");
        HeroNode node10 = new HeroNode(10, "关胜");
        HeroNode node14 = new HeroNode(14, "林冲");



        root.setLeft(node3);
        root.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threaderNodes();

        binaryTree.list();


    }
}


//



// 创建HeroNode节点
@Data
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 设置一个type，0 代表是子树，1 代表是线索【前驱/后继】节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}


class BinaryTree {
    private HeroNode root; // 根节点

    // 为了实现线索化，需要创建一个当前节点指向前驱节点的指针
    // 在递归的时候，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    public void threaderNodes() {
        this.threaderNodes(root);
    }


    // 编写对二叉树中序线索化的方法
    public void threaderNodes(HeroNode node) {
        // 如果说当前节点为空，则不能线索化
        if (node == null) {
            return;
        }

        // 1. 递归到树的叶子节点，例如上面的数就递归到8
        threaderNodes(node.getLeft());

        // 2. 线索化当前节点

        // 2.1 先处理当前节点的前驱节点
        // 8为节点来理解，如果8的left为空
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针的类型
            node.setLeftType(1);
        }

        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // 每处理一个节点后，让当前节点变成下一个节点的前驱节点
        pre = node;


        // 3. 线索化右子树
        threaderNodes(node.getRight());
    }

    public void list() {
        // 1. 定义一个指针
        HeroNode node = root;

        while (node != null) {

            // 递归到叶子节点
            while (node.getLeftType() == 0) {
                node = node.getRight();
            }
            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();

        }

    }


}
