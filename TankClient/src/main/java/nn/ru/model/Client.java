package nn.ru.model;

import dto.DTOClient;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public DTOClient run(Tank tank, DTOClient dtoOut) throws Exception {
        Socket socket = new Socket("localhost", 4011);
        System.out.println("### socket: " + socket);

//        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            ObjectInputStream ois = new ObjectInputStream(in);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(dtoOut);

            DTOClient dtoIn = (DTOClient) ois.readObject();
            tank.A = dtoIn.A;
            System.out.printf("приняли в ответ dtoIn.deltaX = %s\n", dtoIn.deltaX);
            System.out.printf("приняли в ответ dtoIn.deltaY = %s\n", dtoIn.deltaY);
            return dtoIn;
//            ois.close();
//            oos.close();

//        } finally {
//            System.out.println("closing...");
////            socket.close();
//        }
    }
}
