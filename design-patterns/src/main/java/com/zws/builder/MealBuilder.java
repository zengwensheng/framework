package com.zws.builder;

import com.zws.builder.bottle.ChickenBurger;
import com.zws.builder.bottle.VegBurger;
import com.zws.builder.wrapper.Coke;
import com.zws.builder.wrapper.Pepsi;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public class MealBuilder {
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
