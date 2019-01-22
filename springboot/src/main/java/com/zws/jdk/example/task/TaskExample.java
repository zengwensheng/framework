package com.zws.jdk.example.task;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/29
 * 服务器启动会执行一次
 *  因为Timer的sched方法中将TimerTask的nextExecutionTime赋值给当前时间
 *  然后在将TimerTask放到最小堆，然后在Timer类的TimerThread的run方法里面不断的循环
 *  每次取堆中nextExecutionTime和当前系统时间进行比较，如果当前时间大于nextExecutionTime则执行，
 *  如果是单次任务，会将任务从最小堆，移除。否则，更新nextExecutionTime的值
 *
 *  TimerTask是按nextExecutionTime进行堆排序的
 *  单线程 + 最小堆 + 不断轮询
 */
public class TaskExample {
    public static void main(String[] args)throws   Exception {
        Timer timer = new Timer();
        Task task = new Task();
        timer.schedule(task, new Date(), 3000);
    }
}

class Task extends TimerTask {

    @Override
    public void run() {
        System.out.println("Do work...");
    }
}
