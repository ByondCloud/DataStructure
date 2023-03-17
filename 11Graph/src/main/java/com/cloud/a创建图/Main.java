package com.cloud.a创建图;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/13
 * @Time 14:47
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

    // 深度优先算法
    // i 第一次就是0
    public void dfs(boolean[] isVisited, int i) {
        System.out.println(getValueByIndex(i) + "->");
        // 将节点设置为已经访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点
       int w = getFirstNeighbor(i);
       // 找到了邻接节点
       while (w != -1) {
           // 判断是否访问过，没访问就进去标记访问，然后递归继续找下一个
           if (!isVisited[w]) {
               dfs(isVisited, w);
           }
           // 如果w节点已经访问过了
           w = getNextNeighbor(i, w);
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
