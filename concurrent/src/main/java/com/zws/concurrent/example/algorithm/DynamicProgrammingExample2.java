package com.zws.concurrent.example.algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/17
 *
 * 有一个背包，背包容量是M=150kg。有7个物品，物品不可以分割成任意大小。（这句很重要）
 * 要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
 * 物品  A    B    C    D    E    F   G
 * 重量 35kg 30kg 6kg 50kg 40kg 10kg 25kg
 * 价值  10   40  30   50   35   40  30
 *
 */
@Slf4j
public class DynamicProgrammingExample2 {


    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<Item>(){
            {
                add(Item.builder().name("A").weight(35F).price(10F).build());
                add(Item.builder().name("B").weight(30F).price(40F).build());
                add(Item.builder().name("C").weight(6F).price(30F).build());
                add(Item.builder().name("D").weight(50F).price(50F).build());
                add(Item.builder().name("E").weight(40F).price(35F).build());
                add(Item.builder().name("F").weight(10F).price(40F).build());
                add(Item.builder().name("G").weight(25F).price(30F).build());

            }
        };
        Instant instant = Instant.now();

        itemList =  maxVal(itemList,150F);
        itemList.parallelStream().forEach(System.out::println);
        System.out.println("价格："+(itemList.parallelStream().map(Item::getPrice).mapToDouble(Float::doubleValue).sum()));
        System.out.println("时间："+Duration.between(instant,Instant.now()).toMillis());

    }

  private static List<Item> maxVal(List<Item> itemList, Float maxWeight){


        List<Item> tempList  = (List<Item>)((ArrayList)itemList).clone();

        if(tempList.size()==0 || maxWeight==0){
            return new ArrayList<>();
        }

        if(tempList.get(0).getWeight()>maxWeight){
            tempList.remove(0);
            return  maxVal(tempList,maxWeight);
        }

        Item nextItem = tempList.remove(0);

        List<Item> leftToken = maxVal(tempList,maxWeight-nextItem.getWeight());
        Double leftPrice =  leftToken.parallelStream().map(Item::getPrice).mapToDouble(Float::doubleValue).sum()+nextItem.getPrice();

        List<Item> rightToken = maxVal(tempList,maxWeight);
        Double rightPrice =  rightToken.parallelStream().map(Item::getPrice).mapToDouble(Float::doubleValue).sum();

        if(leftPrice>rightPrice){
            leftToken.add(nextItem);
            return leftToken;
        }
        return rightToken;
  }





}


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Item{

    private String name;

    private Float weight;

    private Float price;
}
