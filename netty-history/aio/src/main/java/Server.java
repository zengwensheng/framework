import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 28483 on 2018/8/5.
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("服务端启动失败");
        }
    }

    /**
     * serverSocket.accept() 这个表示服务段可以接受客户端的请求
     * 这里开一个线程的目的是
     * 因为accept() 只能同时接受一次客户端的请求导致其他请求回阻塞
     * 所以想要同时接受多个请求 可以多调用几次start方法，
     */
    public void start() {
        new Thread(() -> {
            doStart();
        });

    }

    public void doStart() {
        try {
            Socket socket = serverSocket.accept();
            new ServerHandler(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
