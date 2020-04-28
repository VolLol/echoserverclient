import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int PORT = 2391;
        Socket socket = null;
        String host = "localhost";
        String sendingMessage = "Message from client o fuck";
        try {
            socket = new Socket(host, PORT);
            System.out.println("Successful connect");

            System.out.println("Send to server");
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(sendingMessage);
            System.out.println("Sending message: " + sendingMessage);
            bufferedWriter.flush();


            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String messageFromServer = bufferedReader.readLine();
            System.out.println("Message from server :" + messageFromServer);

            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
