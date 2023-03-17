package com.cloud.b深度优先;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/14
 * @Time 1:02
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


        // 深度优先遍历
        System.out.println("深度遍历");
        graph.dfs();
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
    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 得到边的书目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回节点i对应的值
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }



    public int getFirstNeighbor(int vertex) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[vertex][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int vertex, int col) {
        for (int i = col + 1; i < vertexList.size(); i++) {
            if (edges[vertex][i] > 0) {
                return i;
            }
        }
        return -1;
    }



    public void dfs(boolean[] isVisited, int vertex) {
        System.out.println(getValueByIndex(vertex) + "->");

        isVisited[vertex] = true;

        int col = getFirstNeighbor(vertex);
        while (col != -1) {
            if (!isVisited[col]) {
                dfs(isVisited, col);
            }
            col = getNextNeighbor(vertex, col);
        }

    }

    // 对dfs进行重载，遍历所有的节点
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


}
