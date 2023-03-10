package com.cloud.i红黑树;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/6
 * @Time 12:31
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RBTree<Integer, Object> rbt = new RBTree();
        for (int i = 10; i < 110; i+=10) {
            rbt.put(i, i);
        }
        TreeOperation.show(rbt.root);



        while (true) {
            System.out.println("请输入key: ");
            String key = scanner.next();
            System.out.println();
            rbt.remove(Integer.valueOf(key));

            // 调用 红黑树控制台打印类
            TreeOperation.show(rbt.root);
        }
    }
}


@SuppressWarnings("all")
class RBTree <K extends Comparable<K>, V>{
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    public Node root; // 根节点

    @Data
    static class Node<K extends Comparable<K>, V> {
        private Node parent; // 父节点
        private Node left; // 左子节点
        private Node right; // 右子节点
        private boolean color; // 颜色
        private K key;
        private V value;

        public Node(K key, V value, Node parent) {
            this.parent = parent;
            this.color = BLACK;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "color=" + (color ? "black" : "red") +
                    ", key=" + key +
                    ", value=" + value +
                    '}';
        }

        public void infixOrder() {
            if (left != null) {
                left.infixOrder();
            }
            System.out.println(this);
            if (right != null) {
                right.infixOrder();
            }
        }

    }

    /**
     * 左旋
     * @param node 根据提供的node去旋转
     */
    private void leftRotate(Node node) {
        if (node != null)  {

            // 1. 保存右子树
            Node newNode = node.right;
            // 2. 本树右节点 = 右子树的左子节点
            node.right = newNode.left;
            if (newNode.left != null) {
                newNode.left.parent = node;
            }
            // 3. 改变父节点指针
            newNode.parent = node.parent;
            // 如果node为根节点
            if (node.parent == null) {
                root = newNode;
                // 判断父节点指针是左指针还是右指针
            } else if (node.parent.left == node) {
                node.parent.left = newNode;
            } else {
                node.parent.right = newNode;
            }
            newNode.left = node;
            node.parent = newNode;
        }
    }

    /**
     * 右旋
     * @param node 根据提供的node去旋转
     */
    private void rightRotate(Node node) {
        if (node != null) {

            // 1. 保存左子树
            Node newNode = node.left;

            // 2. 本树的左子节点 = 左子树的右子节点
            node.left = newNode.right;
            if (newNode.right != null) {
                newNode.right.parent = node;
            }

            // 3. 改变父节点的指针
            newNode.parent = node.parent;
            if (node.parent == null) {
                root = newNode;
            } else if (node.parent.left == node) {
                node.parent.left = newNode;
            } else {
                node.parent.right = newNode;
            }

            // 4. 本树往下挪动到新树
            newNode.right = node;
            node.parent = newNode;

        }
    }

    /**
     * add插入操作
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        // 1. 判断是否为根节点
        Node t = this.root;
        if (t == null) {
            root = new Node(key, (value == null ? key : value), null);
            return;
        }

        Node parent;
        if (key == null) {
            throw new RuntimeException("key为空");
        }

        // 沿着根节点寻找插入位置
        do {
            parent = t;
            if (key.compareTo((K) t.key) < 0) {
                t = t.left;
            } else if (key.compareTo((K) t.key) > 0) {
                t = t.right;
            } else {
                t.setValue(value == null ? key : value);
                return;
            }
        } while (t != null);

        // 以上操作parent就找到了，接下来是判断该节点插入到parent节点的左边还是右边
        // 可以改进
        Node<K, Object> son = new Node<>(key, (value == null ? key : value), parent);
        if (key.compareTo((K) parent.key) < 0) {
            parent.left = son;
        } else {
            parent.right = son;
        }

        // 调整
        fixAfterPut(son);

    }

    // 删除操作
    public V remove(K key) {
        Node node = getNode(key);
        if (node == null) {
            return null;
        }

        V oldValue = (V) node.value;
        deleteNode(node);
        return oldValue;
    }

    // 通过key获取node
    private Node getNode(K key) {
        Node node = this.root;
        while (node != null) {
            int cmp = key.compareTo((K) node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 添加调整
     * @param node
     */
    private void fixAfterPut(Node son) {

        son.color = RED;

        while (son != null && son != root && colorOf(parentOf(son)) == RED) {

            Node father = parentOf(son);
            Node grandFather = parentOf(father);

            /** 如果传入的node不为空 并且 不为根节点 并且 父节点是红色的
             *            4【黑】grandFather
             *           /
             *          2【红】father
             *         /
             *        1【红】son
             */
            if (father == leftOf(grandFather)) {
                /** 意思就是爹在爷爷的左子节点，主要是定位叔叔在哪里
                 *            4【黑】grandFather
                 *           /                 \
                 *          2【红】father        5【红】uncle
                 *         /
                 *        1【红】son
                 */
                // 叔叔节点
                Node uncle = rightOf(grandFather);
                if (colorOf(uncle) == RED) {
                    /** 如果叔叔是红色的节点
                     *
                     *            4【黑】grandFather                   ==>            4【红】grandFather
                     *           /                 \                  ==>           /                 \
                     *          2【红】father        5【红】uncle       ==>          2【黑】father        5【黑】uncle
                     *         /                                      ==>         /
                     *        1【红】son                               ==>        1【红】son
                     */
                    // 爹 黑
                    setColor(father, BLACK);
                    // 叔 黑
                    setColor(uncle, BLACK);
                    // 爷 红
                    setColor(grandFather, RED);

                    son = grandFather;

                } else {
                    /** 如果叔叔是黑色的节点【null就是黑色】【这个是情况一】
                     *            4【黑】grandFather
                     *           /
                     *          3【红】father
                     *         /
                     *        2【红】son
                     */

                    if (son == rightOf(father)) {
                        /** 如果是这种情况，需要旋转2次【null就是黑色】【情况二】
                         *            4【黑】grandFather
                         *           /
                         *          2【红】father
                         *           \
                         *            3【红】son
                         */
                        // son的指针指向
                        son = father;
                        leftRotate(son);
                        /** 调整之后的树长这样
                         *            4【黑】grandFather
                         *           /
                         *          3【红】father
                         *         /
                         *        2【红】son
                         */
                    }

                    // 因为可能会有情况二，因此需要重新获取一下father和grandfather
                    father = parentOf(son);
                    grandFather = parentOf(father);

                    // 爹 黑
                    setColor(father, BLACK);
                    // 爷 红
                    setColor(grandFather, RED);
                    // 根据爷右旋
                    rightRotate(grandFather);
                    /** 调整之后的树长这样
                     *          2【黑】father
                     *         /            \
                     *        1【红】son      4【红】grandFather
                     */
                }
            } else {
                // 意思就是爹在爷爷的右子节点，主要是定位叔叔在哪里
                /**
                 *            4【黑】grandFather
                 *           /                 \
                 *          2【红】uncle         5【红】father
                 *                               \
                 *                                7【红】son
                 */
                // 叔叔节点
                Node uncle = leftOf(grandFather);
                if (colorOf(uncle) == RED) {
                    /** 如果叔叔是红色的节点
                     *
                     *            4【黑】grandFather                               4【红】grandFather
                     *           /                 \                             /                 \
                     *          2【红】uncle         5【红】father                2【黑】father        5【黑】uncle
                     *                               \                                               \
                     *                                7【红】son                                       7【红】son
                     */
                    // 爹 黑
                    setColor(father, BLACK);
                    // 叔 黑
                    setColor(uncle, BLACK);
                    // 爷 红
                    setColor(grandFather, RED);

                    son = grandFather;
                } else {
                    /** 如果叔叔是黑色的节点【null就是黑色】【这个是情况一】
                     /**
                     *            4【黑】grandFather
                     *             \
                     *              5【红】father
                     *               \
                     *                7【红】son
                     */

                    if (son == leftOf(father)) {
                        /** 如果是这种情况，需要旋转2次【null就是黑色】【情况二】
                         *            4【黑】grandFather
                         *             \
                         *              7【红】father
                         *             /
                         *            5【红】son
                         */
                        // son的指针指向
                        son = father;
                        rightRotate(son);
                        /** 调整之后的树长这样
                         *            4【黑】grandFather
                         *             \
                         *              5【红】father
                         *               \
                         *                7【红】son
                         */
                    }

                    // 因为可能会有情况二，因此需要重新获取一下father和grandfather
                    father = parentOf(son);
                    grandFather = parentOf(father);

                    // 爹 黑
                    setColor(father, BLACK);
                    // 爷 红
                    setColor(grandFather, RED);
                    // 根据爷左旋
                    leftRotate(grandFather);
                    /** 调整之后的树长这样
                     *          5【黑】father
                     *         /            \
                     *        4【红】son      7【红】grandFather
                     */
                }
            }
        }
        // 最后要保证root节点必须为黑色
        root.setColor(BLACK);
    }


    /** 找到指定节点的前驱节点【小于node的最大值】
     *              5
     *           /     \
     *          3       7
     *         / \     / \
     *        2   4   6   8
     */
    private Node predecessor(Node node) {
        if (node == null) {
            return null;

            // 例如找节点5，那么他的前驱节点就是4
        } else if (node.left != null) {
            // 前驱节点 先找左子树的头【3】，然后向右循环下去到最底下【4】
            Node preNode = node.left;
            while (preNode.right != null) {
                preNode = preNode.right;
            }
            return preNode;
        } else {

            // 如果是叶子节点，应该不会执行吧2333
            // 例如4的前驱节点是3
            // 例如6的前驱节点是5
            // 我们都需要先向右循环走到头【父节点.left = myself】，然后他的父节点就是我们要找的前驱节点
            System.out.println("代码执行1======================================================");
            Node parent = node.parent;
            Node son = node;
            while (parent != null && son == parent.left) {
                son = parent;
                parent = son.parent;
            }
            return parent;
        }
    }


    /** 找到后继节点【大于node的最小值】
     *              5
     *           /     \
     *          3       7
     *         / \     / \
     *        2   4   6   8
     */
    private Node sucessor(Node node) {
        if (node == null) {
            return null;

            // 例如5的后置节点就是6
        } else if (node.right != null){
            // 后继节点 先找右子树的头，然后向左找
            Node nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;


            // 如果是叶子节点，应该不会执行吧2333
            // 例如4的后继节点是5
            // 例如6的后继节点是7
            // 我们都需要先向左循环走到头【父节点.right = myself】，然后他的父节点就是我们要找的后继节点
        } else {
            System.out.println("代码执行2====================================================");
            Node son = node;
            Node parent = son.parent;
            while (parent != null && parent.right == son) {
                son = parent;
                parent = son.parent;
            }
            return parent;
        }
    }



    // 删除节点
    private void deleteNode(Node node) {
        // 1. 当node有左右子节点的情况
        if (node.left != null && node.right != null) {
            // 找到后继节点
            Node sucessor = sucessor(node);
            // https://blog.csdn.net/IdealSpring/article/details/83780609
            // 你这么理解，形参中的node只是引用，存储在栈中
            // 而下面的node.key 是实际的对象，存储在堆中，这里就是堆中互相的赋值
            node.key = sucessor.key;
            node.value = sucessor.value;
            // node这个引用指向堆中的sucessor这个对象
            node = sucessor;
        }
        // 这行代码就是用来找到需要删除节点的子节点
        // 如果同时拥有左右子节点的话，上面的if块会将需要删除的节点和后继节点交换，最后会变成只有一个子节点或者没有子节点的情况
        Node replacement = (node.left != null ? node.left : node.right);

        if (replacement != null) {
            // 如果需要删除节点的子节点不为空
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            node.left = node.right = node.parent = null;

            // 替换完之后需要调整平衡
            if (node.color == BLACK) {
                fixAfterRemove(replacement);

            }
        } else if (node.parent == null) {
            // 删除节点是根节点的情况
            root = null;

        } else {
            // 叶子节点
            if (node.color == BLACK) {
                // 调整
                fixAfterRemove(node);
            }

            // 删除
            if (node.parent != null) { // 可能是冗余判断？
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else if (node == node.parent.right){
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }

    }

    // 删除调整
    private void fixAfterRemove(Node node) {
        while (node != root && colorOf(node) == BLACK) {
            // 如果节点在左
            if (node == node.parent.left) {
                // 兄弟节点
                Node brother = node.parent.right;
                // 自己是黑的，兄弟是红的
                // 说明兄弟节点一定是4节点
                if (colorOf(brother) == RED) {
                     setColor(brother, BLACK);
                     setColor(node.parent, RED);
                     leftRotate(node.parent);
                     brother = node.parent.right;
                }

                if (colorOf(brother.left) == BLACK && colorOf(brother.right) == BLACK) {
                    // 找兄弟没得借
                    setColor(brother, RED);
                    node = node.parent;

                } else {
                    // 找兄弟有的借

                    if (colorOf(brother.right) == BLACK) {
                        setColor(brother.left, BLACK);
                        setColor(brother, RED);
                        rightRotate(brother);
                        brother = node.parent.right;
                    }
                    setColor(brother, node.parent.color);
                    setColor(node.parent, BLACK);
                    setColor(brother.right, BLACK);
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                // 如果节点在右

                // 兄弟节点
                Node brother = node.parent.left;
                // 判断是不是真正的兄弟节点
                if (colorOf(brother) == RED) {
                    setColor(brother, BLACK);
                    setColor(node.parent, RED);
                    rightRotate(node.parent);
                    brother = node.parent.left;
                }

                if (colorOf(brother.left) == BLACK && colorOf(brother.right) == BLACK) {
                    // 找兄弟没得借
                    setColor(brother, RED);
                    node = node.parent;

                } else {
                    // 找兄弟有的借
                    // 兄弟节点在2-3-4树上是三节点或者是四节点的情况
                    if (colorOf(brother.left) == BLACK) {
                        setColor(brother.right, BLACK);
                        setColor(brother, RED);
                        leftRotate(brother);
                        brother = node.parent.left;
                    }
                    setColor(brother, node.parent.color);
                    setColor(node.parent, BLACK);
                    setColor(brother.left, BLACK);
                    rightRotate(node.parent);
                    node = root;
                }


            }
        }
        // 替代节点是红色，直接染红
        setColor(node, BLACK);
    }







    private Node parentOf(Node node) {
        return node != null ? node.parent : null;
    }

    private Node leftOf(Node node) {
        return node != null ? node.left : null;
    }

    private Node rightOf(Node node) {
        return node != null ? node.right : null;
    }

    private boolean colorOf(Node node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(Node node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    public void infixOrder() {
        root.infixOrder();
    }







}


