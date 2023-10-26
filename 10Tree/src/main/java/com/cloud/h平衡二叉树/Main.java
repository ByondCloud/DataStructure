package com.cloud.h平衡二叉树;

import java.util.Hashtable;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/5
 * @Time 1:43
 */
public class Main {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
//        int[] arr = {10,11,7,6,8,9};
        int[] arr = {1,1,1,1,1,1,1,1,1,1,1};
        // 创建一个AVL树
        Tree tree = new Tree();
        for (int i : arr) {
            tree.add(new Node(i));
        }

//         遍历
        tree.infixOrder();

        // 树的高度
        System.out.println(tree.root.height());
        // 树的左子树高度
        System.out.println(tree.root.left.height());
        // 树的右子树高度
        System.out.println(tree.root.right.height());

    }
}

// 创建AVL树
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

    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    public int rightHeight() {
        if (this.right == null) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    // 返回当前节点的高度，以该节点为根节点的树的高度

    public int height() {
//        三代目
        int maxLeft = 0;
        int maxRight = 0;
        if (this.left != null) {
            maxLeft = left.height();
        }
        if (right != null) {
            maxRight = right.height();
        }
        return Math.max(maxLeft, maxRight) + 1;

//        二代目
//        int maxLeft = (left == null ? 0 : left.height());
//        int maxRight = (right == null ? 0 : right.height());
//        return Math.max(maxLeft, maxRight) + 1;

//        一代目
//        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 左旋左不变，右旋右不变
    // 左旋转的方法
    public void leftRotate() {
        // 1. 创建新的节点（这里的value基本就是root）
        Node newNode = new Node(value);

        // 2. 新节点的左子树设置成当前节点的左子树
        newNode.left = left;

        // 3. 新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;

        // 4. 把当前节点的值换成右子节点的值
        value = right.value;

        // 5. 当前节点的右节点设置为右节点的右节点
        right = right.right;

        // 6. 当前节点的左指针指向新的节点
        left = newNode;
    }

    // 右旋转的方法
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        // 每次添加节点，都去判断一下需不需要平衡
        // 其高的一边的子树也需要保持和整体一致，要么都是右高左低，要么都是左高右低，才能实现一次旋转完成平衡二叉树
        // 如果右高左低，左旋
        if (rightHeight() - leftHeight() > 1) {
            // 如果右子树的左子树高于右子树
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 右子树右旋
                right.rightRotate();
                // 然后找整体左旋
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        // 如果左高右低，右旋
        if (leftHeight() - rightHeight() > 1) {
            // 如果左子树的右子树高 左子树的右子树低
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 左子树左旋
                left.leftRotate();
                // 然后整体右旋
                rightRotate();
            } else {
                rightRotate();
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
