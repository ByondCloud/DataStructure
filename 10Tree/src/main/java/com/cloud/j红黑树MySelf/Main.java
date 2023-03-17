package com.cloud.j红黑树MySelf;


import java.util.Scanner;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/7
 * @Time 14:26
 */
public class Main {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        RBTree rbt = new RBTree();
        for (int i = 10; i < 110; i+=10) {
            rbt.add(i);
        }
        TreeOperation.show(rbt.root);


        while (true) {
            System.out.println("请输入key: ");
            String key = scanner.next();
            System.out.println();
            rbt.delete(Integer.parseInt(key));

            // 调用 红黑树控制台打印类
            TreeOperation.show(rbt.root);
        }

    }


}


class RBTree {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    public Node root; // 根节点


    // 添加操作代码块---------------------------------------------------------------------------------------
    public void add(int value) {
        // 1.判空

        // 2.判根节点
        if (root == null) {
            root = new Node(value, null);
            return;
        }

        // 3.循环查找
        Node inNode = root;
        Node parent = null;
        while (inNode != null) {

            parent = inNode;

            if (inNode.value > value) {
                inNode = inNode.left;
            } else if (inNode.value < value) {
                inNode = inNode.right;
            } else {
                return;
            }
        }

        Node son = new Node(value, parent);
        if (parent.value > value) {
            parent.left = son;
        } else {
            parent.right = son;
        }

        fixAfterAdd(son);


    }

    public void fixAfterAdd(Node son) {
        // 1. 传进来的Node = red
        son.color = RED;
        while (son != null && son != root && son.parent.color == RED) {
            Node father = son.parent;
            Node grandFather = son.parent.parent;
            if (father == grandFather.left) {
                Node uncle = grandFather.right;
                if (colorOf(uncle) == RED) {
                    father.color = BLACK;
                    uncle.color = BLACK;
                    grandFather.color = RED;
                    son = grandFather;
                } else {
                    if (father.right == son) {
                        son = father;
                        // 左旋
                        leftRotate(son);
                    }
                    father = son.parent;
                    grandFather = son.parent.parent;

                    father.color = BLACK;
                    grandFather.color = RED;
                    // 右旋
                    rightRotate(grandFather);
                }
            } else {

                Node uncle = grandFather.left;
                if ((uncle == null ? BLACK : uncle.color) == RED) {
                    father.color = BLACK;
                    uncle.color = BLACK;
                    grandFather.color = RED;
                    son = grandFather;
                } else {
                    if (father.left == son) {
                        son = father;
                        // 左旋
                        rightRotate(son);
                    }
                    father = son.parent;
                    grandFather = son.parent.parent;

                    father.color = BLACK;
                    grandFather.color = RED;
                    // 右旋
                    leftRotate(grandFather);
                }
            }
        }
        root.color = BLACK;
    }
    // 添加操作代码块---------------------------------------------------------------------------------------

    // 删除操作代码块---------------------------------------------------------------------------------------
    public void delete(int value) {
        // 先获取需要删除的node
        Node node = getNode(value);

        if (node == null) {
            return;
        }
        // 如果有双节点
        if (node.left != null && node.right != null) {
            Node nextNode = nextNode(node);
            node.value = nextNode.value;
            node = nextNode;
        }


        Node delNextNode = node.left != null ? node.left : node.right;
        // 1. 假设删除节点只有一个子节点的情况
        if (delNextNode != null) {
            delNextNode.parent = node.parent;
            if (node.parent != null) {

                if (node.parent.left == node) {
                    node.parent.left = delNextNode;
                } else {
                    node.parent.right = delNextNode;
                }
            } else {
                root = delNextNode;
            }
            node.left = node.right = node.parent = null;

            // 颜色判断
            if (node.color == BLACK) {
                // 调整步骤
                fixAfterDelete(delNextNode);
            }

            // 2. 删除节点的父节点为空的情况
        } else if (node.parent == null) {
            root = null;
        } else {
            // 3. 删除节点没有子节点的情况

            if (node.color == BLACK) {
                // 调整
                fixAfterDelete(node);
            }

            // 删除
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    public void fixAfterDelete(Node node) {

        while (node != root && colorOf(node) == BLACK) {
            // 如果需要删除的节点在左边
            if (node.parent.left == node) {
                Node brother = node.parent.right;

                if (colorOf(brother) == RED) {
                    brother.color = BLACK;
                    node.parent.color = RED;
                    leftRotate(node.parent);
                    brother = node.parent.right;
                }

                if (colorOf(brother.left) == BLACK && colorOf(brother.right) == BLACK) {
                    brother.color = RED;
                    node = node.parent;
                } else {
                    if (colorOf(brother.right) == BLACK) {
                        brother.color = BLACK;
                        brother.left.color = RED;
                        rightRotate(brother);
                        brother = node.parent.right;
                    }

                    brother.color = node.parent.color;
                    node.parent.color = BLACK;
                    brother.right.color = BLACK;
                    leftRotate(node.parent);
                    node = root;

                }

            } else {
                Node brother = node.parent.left;

                if (colorOf(brother) == RED) {
                    brother.color = BLACK;
                    node.parent.color = RED;
                    rightRotate(node.parent);
                    brother = node.parent.left;
                }

                if (colorOf(brother.left) == BLACK && colorOf(brother.right) == BLACK) {
                    brother.color = RED;
                    node = node.parent;
                } else {
                    if (colorOf(brother.left) == BLACK) {
                        brother.color = BLACK;
                        brother.right.color = RED;
                        leftRotate(brother);
                        brother = node.parent.left;
                    }

                    brother.color = node.parent.color;
                    node.parent.color = BLACK;
                    brother.left.color = BLACK;
                    rightRotate(node.parent);
                    node = root;

                }

            }
        }
        node.color = BLACK;
    }
    // 删除操作代码块---------------------------------------------------------------------------------------


    // 功能区----------------------------------------------------------------------------------------------
    // 左旋
    public void leftRotate(Node node) {
        // 1. 保存右子树
        Node newNode = node.right;
        // 2.本树右节点 = 右子树的左子节点
        node.right = newNode.left;
        if (newNode.left != null) {
            newNode.left.parent = node;
        }

        // 3. 改变父节点
        newNode.parent = node.parent;
        if (node.parent == null) {
            root = newNode;

        } else if (node.parent.left == node) {
            node.parent.left = newNode;
        } else {
            node.parent.right = newNode;
        }

        newNode.left = node;
        node.parent = newNode;
    }

    // 右旋
    public void rightRotate(Node node) {
        Node newNode = node.left;
        node.left = newNode.right;
        if (newNode.right != null) {
            newNode.right.parent = node;
        }

        newNode.parent = node.parent;
        if (node.parent == null) {
            root = newNode;
        } else if (node.parent.left == node) {
            node.parent.left = newNode;
        } else {
            node.parent.right = newNode;
        }

        newNode.right = node;
        node.parent = newNode;

    }

    // 前驱节点
    public Node preNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.left != null) {
            Node preNode = node.left;
            while (preNode.right != null) {
                preNode = preNode.right;
            }
            return preNode;
        } else {
            Node father = node.parent;
            Node son = node;
            while (father != null && node.parent.left == node) {
                son = father;
                father = son.parent;
            }
            return father;

        }
    }

    // 后继节点
    public Node nextNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            Node nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        } else {
            Node father = node.parent;
            Node son = node;
            while (father != null && node.parent.right == node) {
                son = father;
                father = son.parent;
            }
            return father;

        }
    }

    // 通过value获取Node
    public Node getNode(int value) {
        Node node = root;
        while (node != null) {
            if (node.value > value) {
                node = node.left;
            } else if (node.value < value) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean colorOf(Node node) {
        return node == null ? BLACK : node.color;
    }
    // 功能区----------------------------------------------------------------------------------------------


}


class Node {
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public Node parent; // 父节点
    public Node left; // 左子节点
    public Node right; // 右子节点
    public boolean color = BLACK; // 颜色
    public int value;


    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }
}
