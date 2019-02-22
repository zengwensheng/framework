package com.zws.jdk.example.synchronize;

/**
 * 锁消除
 *
 *关于锁的原理及优化见：笔记1.1-java-java基础-jdk-base-JVM Synchronized
 */
public class LockEliminationExample {

    static int CIRCLE= 2000000;

    /**
     * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks
     *
     * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks
     *
     * @param args
     * @throws InterruptedException
     */


    public static void main(String args[]) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            craeteStringBuffer("JVM", "Diagnosis");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("craeteStringBuffer: " + bufferCost + " ms");
    }

    public static String craeteStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

}
