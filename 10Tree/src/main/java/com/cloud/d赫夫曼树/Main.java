package com.cloud.d赫夫曼树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/2
 * @Time 15:49
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    public static Node createHuffmanTree(int[] arr) {

        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        Collections.sort(nodes);

        while (nodes.size() > 1) {
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 变成一个新的节点
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从list中删除已经处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
            // 将新的节点加入list
            Collections.sort(nodes);
        }
        // 返回哈夫曼树的root节点
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

}

// 节点类
// 让node实现Comparable接口，用途是排序
class Node implements Comparable<Node> {
    int value;
    Node left; // 左指针
    Node right; // 右指针

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
