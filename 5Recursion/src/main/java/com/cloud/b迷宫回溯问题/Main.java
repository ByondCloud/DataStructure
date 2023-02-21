package com.cloud.b迷宫回溯问题;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/2/21
 * @Time 7:42
 */
public class Main {
    public static void main(String[] args) {
        // 8行7列
        int[][] map = new int[8][7];

        // 设置上和下的边界
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 设置左和右的边界
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;


        setWay(map, 1, 1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }



    }

    // 递归回溯来给小球找路
    // map 表示地图
    // i和j 从地图的哪个地方出发 【从1,1出发】
    // 如果小球到达6,5 则路通
    // 0表示没有走过，1表示墙，2表示通路可以走，3表示已经走过，走不通
    // 策略是 下 → 右 → 上 → 左
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; // 假定该点是可以走通的
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if(setWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] 不为0，可能是1,2,3
                return false;
            }

        }
    }

}
