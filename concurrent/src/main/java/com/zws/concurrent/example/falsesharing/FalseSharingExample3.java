package com.zws.concurrent.example.falsesharing;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 */
public class FalseSharingExample3 implements Runnable{
    public static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static FalseSharingExample3.VolatileLong[] longs;
    public static long SUM_TIME = 0l;
    public FalseSharingExample3(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    public static void main(final String[] args) throws Exception {
        Thread.sleep(10000);
        for(int j=0; j<10; j++){
            System.out.println(j);
            if (args.length == 1) {
                NUM_THREADS = Integer.parseInt(args[0]);
            }
            longs = new FalseSharingExample3.VolatileLong[NUM_THREADS];
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new FalseSharingExample3.VolatileLong();
            }
            final long start = System.nanoTime();
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("平均耗时："+SUM_TIME);
    }
    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingExample3(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    // jdk8新特性，Contended注解避免false sharing
    // Restricted on user classpath
    // Unlock: -XX:-RestrictContended
    @sun.misc.Contended
    public final static class VolatileLong{
        public volatile long value = 0L;
    }



}
