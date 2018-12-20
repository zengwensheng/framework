package com.zws.webflux.jdk9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 *
 * jdk 1.9 新特性
 * 响应式编程
 *
 */
public class FlowDemo1 {

    public static void main(String[] args) {
        /**
         * 定义发布者
         * 使用jdk自带的发布者，它实现了Publisher接口
         */
        SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<Integer>();

        /**
         * 定义订阅者
         */
        Flow.Subscriber subscriber = new Flow.Subscriber() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                /**
                 *  保存订阅关系 需要用它来告诉发布者响应
                 */
                this.subscription = subscription;
                /**
                 * 请求一个数据
                 */
                this.subscription.request(1);
            }

            @Override
            public void onNext(Object item) {
                /**
                 * 接受数据
                 */
                System.out.println("接受数据"+item);
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                }
                /**
                 * 处理完数据 调用request再获取一个数据
                 */
                this.subscription.request(1);

                /**
                 *
                 *  调用 cancel 告诉发布者我不需要数据了
                 *  this.subscription.cancel();
                 */


            }

            @Override
            public void onError(Throwable throwable) {
                  // 出现了异常(例如处理数据的时候产生了异常)
                    throwable.printStackTrace();

                    // 我们可以告诉发布者, 后面不接受数据了
                    this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("处理完了!");
            }
        };
        /**
         * 发布者与订阅者 建立关系
         */
        submissionPublisher.subscribe(subscriber);

        for (int i = 0 ;i<3;i++){
            /**
             * submit 是阻塞方法
             */
            submissionPublisher.submit(i);
        }

        submissionPublisher.close();

        try {
            Thread.currentThread().join();
        }catch (Exception e){
        }







    }
}
