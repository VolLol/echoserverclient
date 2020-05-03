import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int PORT = 2020;
        ServerProcessor serverProcessor = new ServerProcessor();
        System.out.println("Server start work");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
        ) {
            System.out.println("Successful connect ");
            serverProcessor.execute(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
