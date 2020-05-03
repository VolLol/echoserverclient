import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        int PORT = 2020;
        String hostName = "localhost";
        ClientProcessor clientProcessor = new ClientProcessor();
        System.out.println("Client start work");
        try (Socket socket = new Socket(hostName, PORT);
        ) {
            System.out.println("Successful connect");
            clientProcessor.execute(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
