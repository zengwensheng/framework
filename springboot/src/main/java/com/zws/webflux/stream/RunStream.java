package com.zws.webflux.stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/12
 *
 * 1: 所用的操作是链式调用，一个元素只迭代一次
 * 2: 每个中间操作返回一个行的流，流里面有个属性sourceStage
 *  指向同一个地方，就是head
 * 3: head->nextState->nextState->...->null
 * 4：有状态操作会把无状态操作阶段，单独处理
 * 5: 并行环境下，有状态的中间操作 会重新执行一次迭代 然后在重新迭代一次下面所有的无状态操作
 * 6: parallel/sequetial 这两个操作也是中间操作（也是返回stream）
 *    但是他们不创建流，他们只修改 Head的并行标志]
 *  运行原理详见： 笔记1.1-java-java基础-jdk-stream
 */
public class RunStream {

    public static void main(String[] args) {
        Random random = new Random();

        //随机产生数据
        Stream<Integer>  stream = Stream.generate(()->random.nextInt())
                //无限流需要短路操作
                .limit(500)
                //无状态操作
                .peek(s->print("peek:"+s))
                //无状态操作
                .filter(s->{
                    print("filter:"+s);
                    return s>1000000;
                })
                //有状态操作
                .sorted((i1,i2)->{
                    print("排序:"+i1+","+i2);
                    return i1.compareTo(i2);
                })
                //无状态操作
                .peek(
                      s->{
                          print("peek2:"+s);
                      }
                );
        //终止操作
        stream.count();

        IntStream.rangeClosed(1,100).parallel().sum();
    }

    /**
     * 打印日志并sleep 5 毫秒
     *
     * @param s
     */
    public static void print(String s) {
        // System.out.println(s);
        // 带线程名(测试并行情况)
        System.out.println(Thread.currentThread().getName() + " > " + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
    }
}
