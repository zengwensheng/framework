package com.zws.webflux.jdk9;

/*import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

*//**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 *
 * 带process 的flow demo
 *
 * Processor, 需要继承SubmissionPublisher并实现Processor接口
 *
 * 过滤小雨0的数字
 *//*

class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer,String>  {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        *//**
         *  保存订阅关系 需要用它来告诉发布者响应
         *//*
        this.subscription = subscription;
        *//**
         * 请求一个数据
         *//*
        this.subscription.request(1);

    }

    @Override
    public void onNext(Integer item) {
        *//**
         * 接受数据
         *//*
        System.out.println("处理器接受数据："+item);

        if(item>0){
            this.submit("处理过的数据"+item);
        }

        this.subscription.request(1);


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
        System.out.println("处理器处理完了!");
    }
}
public class FlowDemo2 {

    public static void main(String[] args) {


        *//**
         * 定义发布者
         * 使用jdk自带的发布者，它实现了Publisher接口
         *//*
         SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<>();


        *//**
         * 定义处理器
         *//*
        MyProcessor myProcessor = new MyProcessor();

        *//**
         * 发布者与处理器建立关系
         *//*
        submissionPublisher.subscribe(myProcessor);

        *//**
         * 定义订阅者
         *//*
        *//**
         * 定义订阅者
         *//*
        Flow.Subscriber subscriber = new Flow.Subscriber() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                *//**
                 *  保存订阅关系 需要用它来告诉发布者响应
                 *//*
                this.subscription = subscription;
                *//**
                 * 请求一个数据
                 *//*
                this.subscription.request(1);
            }

            @Override
            public void onNext(Object item) {
                *//**
                 * 接受数据
                 *//*
                System.out.println("接受数据"+item);

                *//**
                 * 处理完数据 调用request再获取一个数据
                 *//*
                this.subscription.request(1);

                *//**
                 *
                 *  调用 cancel 告诉发布者我不需要数据了
                 *  this.subscription.cancel();
                 *//*


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
        *//**
         * 订阅者与处理器建立关系
         *//*
        myProcessor.subscribe(subscriber);

        *//**
         * 发送数据
         *//*
        submissionPublisher.submit(111);
        submissionPublisher.submit(-111);


        submissionPublisher.close();

        try {
            Thread.currentThread().join();
        }catch (Exception e){
        }

    }
}*/
