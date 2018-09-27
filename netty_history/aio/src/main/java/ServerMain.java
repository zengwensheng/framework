/**
 * Created by 28483 on 2018/8/5.
 */
public class ServerMain {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;

    public static void main(String[] args) {
            Server server = new Server(PORT);
            server.start();
    }
}
