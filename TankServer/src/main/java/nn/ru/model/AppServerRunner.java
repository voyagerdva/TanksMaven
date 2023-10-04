package nn.ru.model;

import static java.lang.Thread.sleep;

public class AppServerRunner {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();

    }
}
