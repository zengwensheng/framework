package com.zws.webflux.servlet3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 */
@WebServlet("/sync")
public class SyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Instant instant = Instant.now();
        doSomething(req,resp);
        System.out.println("耗时:"+Duration.between(instant,Instant.now()).toMillis());
    }

    private  void doSomething(HttpServletRequest req, HttpServletResponse resp){

        try {

            TimeUnit.SECONDS.sleep(5);
            resp.getWriter().write("none");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doPost(req,resp);
    }
}
