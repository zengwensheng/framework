package com.zws.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/13
 *
 * fork/join 使用的是 工作窃取算法
 *
 * @TODO 源码还未解析
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Long> {
    public static final int threshold = 500000000;
    private long start;
    private long end;

    public ForkJoinTaskExample(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            long middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            long leftResult = leftTask.join();
            long rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {



        long startNum =0;
        long endNum = 2000000000;

        Instant start = Instant.now();
        long l =  LongStream.rangeClosed(startNum,endNum).parallel().reduce(0,Long::sum);
        log.info("java 8 并行流 结果：{}",l);
        log.info("java 8 并行流 耗费时间：{}"+Duration.between(start,Instant.now()).toMillis());

        start = Instant.now();
         l =  LongStream.rangeClosed(startNum,endNum).sequential().reduce(0,Long::sum);
        log.info("java 8 顺序流 结果：{}",l);
        log.info("java 8 顺序流 耗费时间：{}"+Duration.between(start,Instant.now()).toMillis());

        start = Instant.now();

        ForkJoinPool forkjoinPool = new ForkJoinPool();
        //生成一个计算任务，计算1+2+3+4

        ForkJoinTaskExample task = new ForkJoinTaskExample(startNum,endNum);

        //执行一个任务
        Future<Long> result = forkjoinPool.submit(task);
        try {
            log.info("java 7 fork/join 框架 result:{}", result.get());
        } catch (Exception e) {
            log.error("exception", e);
        }

        log.info("java 7 fork/join 耗费时间：{}"+Duration.between(start,Instant.now()).toMillis());
    }

}
