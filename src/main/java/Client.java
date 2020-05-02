import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 2002;

        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);//считывает исходящий поток
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//считывает входящий поток
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))//cчитывает строку
        ) {
            System.out.println("Client start working. Print you string");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Server answer: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
