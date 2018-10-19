package com.zws.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public class Meal {

    private List<Item> itemList = new ArrayList<>();

    public void addItem(Item item){
        itemList.add(item);
    }

   public float getCost(){
        float cost = 0.0f;
        for(Item item:itemList){
            cost += item.price();
        }
        return cost;
   }

   public void showItems(){
        for(Item item:itemList){
            System.out.println("item:"+item.name());
            System.out.println(",packing:"+item.packing().pack());
            System.out.println(", price:"+item.price());
        }
   }

}
