package com.zws.webflux.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/12
 *
 */
public class RunStream {

    static Random random = new Random();

    public static Integer randomInteger() {
        return random.nextInt();
    }

    public static void main(String[] args) {

        /**
         *
         * api分为中间操作 ，结束操作
         * 中间操作分为 有状态操作，无状态操作
         * 结束操作分为 短路操作 非短路操作
         *
         * 惰性求值: 只有调用了终止操作才会运行代码，如果只是调用了中间操作是不会去计算的
         *
         * 疑问：
         * 一：如何记录操作？
         *    每个中间操作返回一个行的流，流里面有个属性sourceStage 指向同一个地方，就是head
         *    head->nextState->nextState->...->null ，返回一个ReferencePipeline的双向链表
         *
         * 二：操作是如何叠加的？
         *   每一个ReferencePipeline有一个opWrapSink会返回一个Sink接口的实例
         *   然后Sink中会回调自己定义的函数接口实现，然后在转给下一个Sink
         *
         * 三：叠加之后的操作如何执行的 ？
         * 串行：是在终止操作中执行的,调用了AbstractPipeline的wrapAndCopyInto
         * 这里面分别调用了wrapSink和copyInto方法
         * wrapSink 循环调用了ReferencePipeline的双向链表中的opWrapSink，从尾部开始
         * 最后获得了一个Sink的单向链表，对应这流的调用的顺序
         * copyInto 先调用Sink的start 方法 然后start依次下一个Sink的start 通知Sink做好准备
         *   然后调用accept 方法 然后回调你自己的函数接口，然后在传个下另一个流
         *      当遇到有状态的操作时，accept 方法 会把你传递的值给保存，不会转给下一个Sink
         *   调用end 方法，先调用Sink的end 方法 然后end依次下一个Sink的end  这个是专门用来通知
         *   有状态操作我前面的无状态操作已经全部迭代完了，请开始你的操作（比如：排序）
         *   当排序完之后回在开启一次迭代，通知下面的Sink start accept end 方法
         *   当Sink是短路操作，就会停止迭代，调用Sink中cancellationRequested这个方法判断的
         *
         * 四：执行后的结果在那里 ？
         * 串行：
         *   在调用collect的方法中 调用了ReduceOps.makeRef(collector) 这个生成了一个ReduceOps
         *   然后调用evaluateSequential方法，然后创建ReduceSink对象，然后放到Sink的单向链表的最后
         *   然后 每次迭代的值追加到这个对象的state属性上
         *
         *
         * parallel/sequetial 这两个操作也是中间操作（也是返回stream）
         * 但是他们不创建流，他们只修改 Head的并行标志]
         *
         * 运行原理详见： 笔记1.1-java-java基础-jdk-stream
         */

        List<String> list =  new ArrayList<String>(){{
           add("bcd");
           add("cde");
           add("def");
           add("abc");
        }};
        list = list.stream()
                .parallel()
                .filter(e->e.length()>=3)
                .map(e->e.charAt(0))
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(list);

        /**
         *
         * 并行原理： fork/join
         *
         *  1: 所用的操作是链式调用，一个元素只迭代一次
         *  2: 每个中间操作返回一个行的流，流里面有个属性sourceStage
         *   指向同一个地方，就是head
         *  3: head->nextState->nextState->...->null
         *  4：有状态操作会把无状态操作阶段，单独处理
         *  5: 并行环境下，有状态的中间操作 会重新执行一次迭代 然后在重新迭代一次下面所有的无状态操作
         *  6:
         */
        //随机产生数据
      /*  Stream<Integer> stream = Stream.generate(RunStream::randomInteger)
                //无限流需要短路操作
                .limit(500);
        stream.count();

        stream = stream
                //无状态操作
                .peek(s -> print("peek:" + s))
                //无状态操作
                .filter(s -> {
                    print("filter:" + s);
                    return s > 1000000;
                })
                //有状态操作
                .sorted((i1, i2) -> {
                    print("排序:" + i1 + "," + i2);
                    return i1.compareTo(i2);
                })
                //无状态操作
                .peek(
                        s -> {
                            print("peek2:" + s);
                        }
                );
        //终止操作
        stream.count();*/



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
