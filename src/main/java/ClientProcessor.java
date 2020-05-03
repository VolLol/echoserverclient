import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcessor {
    //  ClientProcessor - логика отправки и получния данных на сервер

    public void execute(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Please, write command");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String responseFromServer;
                while ((responseFromServer = in.readLine()) != null) {
                    System.out.println("Server answer: " + responseFromServer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
