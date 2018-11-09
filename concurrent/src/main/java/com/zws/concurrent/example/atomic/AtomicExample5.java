package com.zws.concurrent.example.atomic;

import com.zws.concurrent.annoations.Recommend;
import com.zws.concurrent.annoations.ThreadSafe;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/6
 * AtomicIntegerFieldUpdater就是用来更新某一个实例对象里面的int属性的
 *
 * 用法规则：
 * 字段必须是volatile类型的，在线程之间共享变量时保证立即可见
 * 字段的描述类型（修饰符public/protected/default/private）是与调用者与操作对象字段的关系一致。也就是说调用者能够直接操作对象字段，那么就可以反射进行原子操作。
 * 对于父类的字段，子类是不能直接操作的，尽管子类可以访问父类的字段。
 * 只能是实例变量，不能是类变量，也就是说不能加static关键字。
 * 只能是可修改变量，不能使final变量，因为final的语义就是不可修改。
 * 对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修改其包装类型（Integer/Long）。如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    private volatile  int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }

    }

}
