package com.cloud.e赫夫曼编码;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/2
 * @Time 22:27
 */


import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/2
 * @Time 15:49
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();

        byte[] bytes1 = huffmanZip(bytes);

        byte[] sourceBytes = decode(huffmanCodes, bytes1);
        System.out.println(new String(sourceBytes));



    }


    /**
     * 封装
     * @param bytes 给[i, , l, i, k, e, ...]
     * @return 返回 [-88, -65, -56, ...]
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 1. 将byte塞入列表中
        List<Node> nodes = getNodes(bytes);
        // 2. 创建哈夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        // 3. 得到哈夫曼编码
        Map<Byte, String> codes = getCodes(huffmanTree);
        // 4. 根据生成的赫夫曼编码，压缩得到哈夫曼编码字节数组
        byte[] zip = zip(bytes, codes);

        return zip;
    }


    // 把对应byte封装到node对象，然后用ArrayList组合起来
    private static List<Node> getNodes(byte[] bytes) {

        List<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每个bytes出现的次数，用map来统计
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            // 先保存下来key对应的value
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把map转换成对应的Node对象，放到nodes集合中去
//        counts.forEach((aByte, integer) -> nodes.add(new Node(aByte, integer)));
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 生成哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {

            // 先排序
            Collections.sort(nodes);


            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);

        }
        return nodes.get(0);
    }


    // 生成哈夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", stringBuilder);
        return huffmanCodes;

    }
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                // 表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    // i like... => [[10101000],[11010101]...]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        // i like... => 10101000...
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 统计huffmanCodeBytes的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建一个存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        // 记录huffmanCodeBytes的下标
        int index = 0;
        // 因为每8位对应一个byte
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String strByte;
            // 如果不够8位
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将字符串"10101000" 先转成Integer，然后再转成byte
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;

    }

    // 哈夫曼数组转成哈夫曼编码二进制字符串
    private static String byteToBitString(boolean flag, byte b) {
        // flag = true 表示需要补高位0
        // 把byte转成int
        int temp = b;

        if (flag) {
            // 或运算，有1得1，全0得0
            // 这里这么做的目的就是给正数补0
            // 最后一个数组不能补0，补了就乱了
            temp |= 256;
        }
        // 返回的是temp对应的二进制的补码
        // toBinaryString 十进制转换成字符串的二进制
        // 如果是正数，则相安无事，如果是负数，因为int型占4个字节，所以会有32位
        String str = Integer.toBinaryString(temp);
        if (flag) {
            // 如果是正数，则相安无事，如果是负数，因为int型占4个字节，所以会有32位
            // 因为如果是负数，他的补码将会是32位，我们只需要截取最后的8位就行了
            return str.substring(str.length() - 8);
        }
        return str;
    }

    /**
     * 把哈夫曼二进制转换成具体的字符串
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 通过哈夫曼编码表得到的哈夫曼数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 保存解析后的哈夫曼编码的【1010100010111111110010001...】
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i != huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(flag, huffmanBytes[i]));
        }



        // 把字符串按哈夫曼编码表进行解码
        // 1. 反转map
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建一个集合 存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 取出一个'1'或'0'
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    public static void zipFile(String srcFile, String stFile) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(srcFile);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);


        byte[] huffmanBytes = huffmanZip(bytes);

        // 存放压缩文件
        FileOutputStream fileOutputStream = new FileOutputStream(stFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(huffmanBytes);
        objectOutputStream.writeObject(huffmanCodes);

        fileInputStream.close();
        fileInputStream.close();
        objectOutputStream.close();
    }


}

// 节点类
// 让node实现Comparable接口，用途是排序
class Node implements Comparable<Node> {
    Byte data; // 存放具体的字母
    Integer weight; // 权值 就是字母出现的次数
    Node left; // 左指针
    Node right; // 右指针

    public Node(Byte date, Integer weight) {
        this.data = date;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + data +
                ", weight=" + weight +
                '}';
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

