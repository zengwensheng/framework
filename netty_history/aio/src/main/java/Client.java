import java.io.IOException;
import java.net.Socket;

/**
 * Created by 28483 on 2018/8/5.
 */
public class Client {

    private static final int SLEEP_TIME = 5000;


    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(ServerMain.HOST, ServerMain.PORT);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户端启动成功!");
                while (true) {
                    try {
                        String message = "hello world";
                        System.out.println("客户端发送数据: " + message);
                        socket.getOutputStream().write(message.getBytes());
                    } catch (Exception e) {
                        System.out.println("写数据出错!");
                    }
                    sleep();
                }


            }
        }).start();

    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
