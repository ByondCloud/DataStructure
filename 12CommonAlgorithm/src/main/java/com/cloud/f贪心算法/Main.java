package com.cloud.f贪心算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author ByondCloud
 * @version 1.0
 * @Date 2023/3/16
 * @Time 6:07
 */
public class Main {
    public static void main(String[] args) {

        // 创建电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        // 放电台
        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");

        HashSet<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");

        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");

        HashSet<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");

        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");

        broadcasts.put("k1", k1);
        broadcasts.put("k2", k2);
        broadcasts.put("k3", k3);
        broadcasts.put("k4", k4);
        broadcasts.put("k5", k5);

        HashSet<String> allAreas = new HashSet<>();

        broadcasts.forEach((key, value) -> allAreas.addAll(value));


        // 准备阶段----------------------------------------------------


        // 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 临时set
        HashSet<String> tempSet = new HashSet<>();
        // 所能覆盖的最大key
        String maxKey = null;
        int maxCity = 0;


        while (allAreas.size() != 0) {

            maxKey = null;

            for (String key : broadcasts.keySet()) {

                tempSet.clear();

                // 所选key所包含的地区
                tempSet.addAll(broadcasts.get(key));

                // 取出共同的
                tempSet.retainAll(allAreas);


                // 如果当前集合包含未覆盖地区
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxCity)) {
                    maxKey = key;
                    maxCity = tempSet.size();
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                // 清除
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }

        System.out.println("贪婪算法得到的结果");
        System.out.println(selects);



    }
}
