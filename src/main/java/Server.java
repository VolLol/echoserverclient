import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int PORT = 2391;
        Socket socketToClient = null;
        String host = "localhost";
        try {
            System.out.println("Server starting");
            serverSocket = new ServerSocket(PORT);
            socketToClient = serverSocket.accept();
            System.out.println("Sucsessful connect");


            InputStream inputStream = socketToClient.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String messageFromClient = bufferedReader.readLine();
            System.out.println("Message from client is: " + messageFromClient);


            OutputStream outputStream = socketToClient.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String messageFromServer = "IAMALIVE";
            bufferedWriter.write(messageFromServer);
            System.out.println("Send message for client like this:" + messageFromServer);
            bufferedWriter.flush();

            socketToClient.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}