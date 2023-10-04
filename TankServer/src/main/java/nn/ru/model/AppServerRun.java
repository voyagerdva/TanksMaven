package nn.ru.model;

import static java.lang.Thread.sleep;

public class AppServerRun {
    public static String getInfo() {
        return "Hello from Server!!!";
    }
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();

    }
}
