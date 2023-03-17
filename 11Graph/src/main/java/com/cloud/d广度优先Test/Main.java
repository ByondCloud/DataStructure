package com.cloud.d广度优先Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/14
 * @Time 17:45
 */
public class Main {

    public static void main(String[] args) {
        int n = 5; // 确定节点个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);

        // 通过循环添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边
        // 代表A和B连接
        graph.insertEdge(0, 1, 1);
        // 代表A和C连接
        graph.insertEdge(0, 2, 1);
        // 代表B和C连接
        graph.insertEdge(1, 2, 1);
        // 代表B和D连接
        graph.insertEdge(1, 3, 1);
        // 代表B和E连接
        graph.insertEdge(1, 4, 1);

        // 显示邻接矩阵
        graph.showGraph();


        // 广度优先遍历
        System.out.println("广度遍历");
        graph.bfs();
    }
}


class Graph {
    private ArrayList<String> vertexList; // 存储顶点的集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 记录边数
    private boolean[] isVisited;

    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<>();
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 常用的方法

    // 返回节点i对应的值
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }


    // 显示矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 广度优先算法
    // i 第一次就是0
    public void bfs(boolean[] isVisited, int i) {


        // 传进来的vertex先表示已经经过了
        isVisited[i] = true;
        System.out.println(i);

        // 由于队列是先进先出，符合我们广度优先
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);


        Integer vertex;
        Integer neighbor;

        while (queue.isEmpty()) {
            // 出列
            vertex = queue.removeFirst();
            neighbor = getFirstNeighbor(vertex);
            while (neighbor != -1) {
                if (!isVisited[neighbor]) {
                    System.out.println(neighbor);
                    isVisited[neighbor] = true;
                    queue.addLast(neighbor);
                }
                neighbor = getNextNeighbor(vertex, neighbor);
            }
        }
    }

    // 遍历所有的节点，都进行广度优先
    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            bfs(isVisited, i);
        }
    }




}
