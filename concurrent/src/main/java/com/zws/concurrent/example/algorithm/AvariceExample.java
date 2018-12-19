package com.zws.concurrent.example.algorithm;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/17
 *
 * 五大算法之一 贪婪算法
 *  是指在对问题进行求解时，在每一步选择中都采取最好或者最优(即最有利)的选择，从而希望能够导致结果是最好或者最优的算法。
 *  贪婪算法所得到的结果往往不是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果。
 *
 * 假设存在如下表的需要付费的广播台，以及广播台信号可以覆盖的地区。 如何选择最少的广播台，让所有的地区都可以接收到信号。
 *
 *   - - - - - - - - - - - - - - -
 *  |   广播台     |    覆盖地区     |
 *  |    k1       |      ID,NV,UT  |
 *  |    K2       |     WA,ID,MT   |
 *  |    K3       |     OR,NV,CA   |
 *  |    K4       |     NV,UT      |
 *  |    K5       |     CA,AZ      |
 *  |   ....      |     ....       |
 *   - - - - - - - - - - - - - - -
 *   原理详见 笔记1.1-算法-五大算法之一 贪婪算法
 */

public class AvariceExample {

    public static void main(String[] args) {

        //初始化广播台信息
        HashMap<String,HashSet<String>> broadcasts = new HashMap<String,HashSet<String>>();
        broadcasts.put("K1", new HashSet(Arrays.asList(new String[] {"ID","NV","UT"})));
        broadcasts.put("K2", new HashSet(Arrays.asList(new String[] {"WA","ID","MT"})));
        broadcasts.put("K3", new HashSet(Arrays.asList(new String[] {"OR","NV","CA"})));
        broadcasts.put("K4", new HashSet(Arrays.asList(new String[] {"NV","UT"})));
        broadcasts.put("K5", new HashSet(Arrays.asList(new String[] {"CA","AZ"})));

        //需要覆盖的全部地区
        HashSet<String> allAreas = new HashSet(Arrays.asList(new String[] {"ID","NV","UT","WA","MT","OR","CA","AZ"}));

        //所选择的广播台列表
        List<String> selects = new ArrayList<>();

        HashSet<String> tempSet = new HashSet<String>();

        String previousKey;

        /**
         * 每次在集合中找到交集做都的那个key
         * 将key放入到selects中
         * 然后在找第二个
         * 直到地区allAreas的size 为0
         */


        while(allAreas.size()!=0){
            previousKey = null;
            for(String key:broadcasts.keySet()){
                tempSet.clear();
                tempSet.addAll(broadcasts.get(key));
                //求出2个集合的交集，此时tempSet会被赋值为交集的内容，所以使用临时变量
                tempSet.retainAll(allAreas);
                //如果该集合包含的地区数量比原本的集合多
                if(tempSet.size()>0&&(previousKey==null|| (tempSet.size()>broadcasts.get(previousKey).size()))){
                    previousKey = key;
                }
            }
            if(previousKey!=null){
                selects.add(previousKey);
                allAreas.removeAll(broadcasts.get(previousKey));
            }
        }
        System.out.println(selects);

    }
}
