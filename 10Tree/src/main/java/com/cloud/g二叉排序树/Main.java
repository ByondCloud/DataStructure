package com.cloud.g二叉排序树;

import lombok.Data;
import sun.text.resources.th.BreakIteratorInfo_th;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/4
 * @Time 20:42
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        Tree tree = new Tree();
        for (int i : arr) {
            tree.add(new Node(i));
        }

        tree.delNode(2);
        tree.delNode(5);
        tree.delNode(9);
        tree.delNode(12);
        tree.delNode(7);
        tree.delNode(3);
        tree.delNode(10);
        tree.delNode(1);

        tree.infixOrder();
    }
}


class Tree {
    // 根节点
    public Node root;
    // 添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 查找需要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找需要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 找到右子树最小的那个数
     * @param node 传入需要删除节点的右子节点
     * @return 右子树最小的那个数
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public int delLeftTreeMax(Node node) {
        Node target = node;
        while (target.right != null){
            target = target.right;
        }
        delNode(target.value);
        return target.value;

    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        }

        // 需要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }

        // 如果该节点是根节点，并且整棵树就一个root节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }


        // 找到需要删除节点的父节点
        Node parent = searchParent(value);

        if (targetNode.left == null && targetNode.right == null) { // 如果需要删除的节点是叶子节点

            if (parent.left != null && parent.left == targetNode) { // 判断需要删除的节点是父节点的左节点还是右节点
                parent.left = null;
            } else if (parent.right != null && parent.right == targetNode) {
                parent.right = null;
            }


        } else if (targetNode.left != null && targetNode.right != null) { // 如果需要删除的节点有左右子节点
            // 从右子树找到最小的，和你需要删除的进行替换
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;

            // 练习 从左边找到最大的
//            int maxVal = delLeftTreeMax(targetNode.left);
//            targetNode.value = maxVal;


        } else { // 只有一个子树的节点

            // 如果目标节点的左节点不为空
            if (targetNode.left != null) {
                if (parent == null) {
                    root = targetNode.left;
                } else {
                    if (parent.left == targetNode) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                }
            } else {
            // 右节点不为空
                if (parent == null) {
                    root = targetNode.right;
                } else {
                    if (parent.left == targetNode) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }

            }
        }

    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

}



class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 添加节点
    // 递归添加，满足二叉排序树
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入的节点的值是否大于当前node
        if (node.value < this.value) {
            // 如果左指数为空
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归
                this.left.add(node);
            }
        } else {
            if (node.value > this.value) {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }
    }

    // 查找需要删除的节点
    public Node search(int value) {
        if (value == this.value) { // 找到
            return this;
        }
        if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }



    /**
     * 查找需要删除节点的父节点
     * @param value     需要删除的节点的值
     * @return          需要删除节点的父节点
     */
    public Node searchParent(int value) {
        // 如果this节点的左右节点 == value，就表示this是需要找的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //
            if (value < this.value && this.left != null) {
                // 向左子树递归
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                // 向右递归
                return this.right.searchParent(value);
            } else {
                // 妹找到
                return null;
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}

