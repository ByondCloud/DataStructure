package com.cloud.a二叉数;

import lombok.Data;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/28
 * @Time 18:15
 */
public class Main {
    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "公孙胜");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "林冲");
        HeroNode node7 = new HeroNode(7, "秦明");


        // 先手动创建二叉树
        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);


        binaryTree.setRoot(root);
        // 前序遍历
        binaryTree.preOrder();



        // 中序遍历
//        binaryTree.infixOrder();

        // 后序遍历
//        binaryTree.postOrder();

        // 前序查找
//        HeroNode heroNode = binaryTree.preOrderSearch(5);
//        if (heroNode != null) {
//            System.out.println(heroNode);
//        } else {
//            System.out.println("没找到");
//        }

        // 中序查找
//        HeroNode heroNode = binaryTree.infixOrderSearch(1);
//        if (heroNode != null) {
//            System.out.println(heroNode);
//        } else {
//            System.out.println("没找到");
//        }

        // 后序查找
//        HeroNode heroNode = binaryTree.postOrderSearch(1);
//        if (heroNode != null) {
//            System.out.println(heroNode);
//        } else {
//            System.out.println("没找到");
//        }

    }
}

class BinaryTree {
    private HeroNode root; // 根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no) {
        if (root != null) {
            // 判断root是不是就是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }



}

// 创建HeroNode节点
@Data
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    // 前序遍历
    public void preOrder() {
        System.out.println(this); // 先输出父节点
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);

        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    // 前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        if (this.no == no) {
            return this;
        }

        // 递归向左子树前序遍历
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // 递归向右子树遍历
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        System.out.println("进入中序查找");
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }

        // 右边中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // 如果左子树没有找到，就到右边找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }


    // 递归删除节点
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 向左子树递归
        if (this.left != null) {
            this.left.delNode(no);
        }

        // 向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }


}


