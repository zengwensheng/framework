package com.zws.webflux.servlet3;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 * server 3.0 提供异步servlet 只是让web服务器能承载更多的请求 不是提供性能
 *  因为这里是不让web服务器产生的接受http请求线程不阻塞，然后重新开一个工作线程去执行
 *  详细见 笔记1。1 -java-源码分析-spring boot-spring webflux -server 3。0
 */
@WebServlet(urlPatterns = "/async",asyncSupported=true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instant instant = Instant.now();

        AsyncContext  asyncContext= req.startAsync();

        /**
         *
         * 注意这里不要用acontext.start启动一个线程
         * 因为这个有可能用的线程池与http线程池是一致的
         *
         */
        CompletableFuture.runAsync(()-> doSomething(asyncContext));

        System.out.println("耗时:"+Duration.between(instant,Instant.now()).toMillis());
    }

    private  void doSomething(AsyncContext asyncContext){

        try {

            TimeUnit.SECONDS.sleep(5);
            asyncContext.getResponse().getWriter().write("none");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncContext.complete();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doPost(req,resp);
    }
}
