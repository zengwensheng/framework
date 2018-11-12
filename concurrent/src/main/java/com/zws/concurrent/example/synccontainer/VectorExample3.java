package com.zws.concurrent.example.synccontainer;

import com.zws.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/12
 */
@Slf4j
@NotThreadSafe
public class VectorExample3 {

    /**
     * java.util.ConcurrentModificationException
     * 如果删除的元素是最后一次循环则不会报错  这个底层调用的是Iterator 所以第一个和第二个一样
     */
    private static void test1(Vector<Integer> vector){
        for (Integer num:vector){
            if(num.compareTo(1)==0){
                vector.remove(num);
            }
        }
    }


    /**
     *  java.util.ConcurrentModificationException
     *  如果删除的元素是最后一次循环则不会报错  因为这个Iterator为了保证数据的强一致性，在查询时不能删除或添加，否则报错
     *  备注：ConcurrentHashMap 是弱一致性，如果这样是不会报错的
     */

    private static void test2(Vector<Integer> vector){
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if(i.compareTo(2)==0){
                vector.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> vector){
        for(int i=0;i<vector.size();i++){
            if(vector.get(i).compareTo(3)==0){
                vector.remove(i);
            }
        }
    }







    public static void main(String[] args) throws Exception{
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        try {
            test1(vector);
        }catch (Exception e){
            log.error("test1 error:{}",e);
        }
        try {
            test2(vector);
        }catch (Exception e){
            log.error("test2 error:{}",e);
        }

        try {
            test3(vector);
        }catch (Exception e){
            log.error("test3 error:{}",e);
        }




    }


}
