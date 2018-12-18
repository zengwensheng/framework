package com.zws.concurrent.example.algorithm;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/17
 *
 * 有一个背包，背包容量是M=150kg。有7个物品，物品不可以分割成任意大小。（这句很重要）
 * 要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
 * 物品 A B C D E F G
 * 重量 35kg 30kg 6kg 50kg 40kg 10kg 25kg
 * 价值  10   40  30   50   35   40  30
 *
 */
@Slf4j
public class DynamicProgrammingExample2 {


  private void maxValue(List oraSet, List leftRoot){

  }

}


@Data
class Item{

    private String name;

    private Integer weight;

    private String value;
}
