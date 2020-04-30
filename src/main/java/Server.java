import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    public static void main(String[] args) {
        int portNumber = 2000;
        System.out.println("Server start working");
        System.out.println("----------------------------------------------");

        try (ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;//что выйдет в строке со стороны сервера и что пришло от польователя
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client write: " + inputLine);
                Server server = new Server();
                server.command(inputLine, out, in);


            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public PrintWriter command(String inputLine, PrintWriter out, BufferedReader in) {
        if (inputLine.equals("v")) {
            System.out.print("Client ask about version. Return version for him" + "\n");
            out.println("version is 123456");
        } else if (inputLine.equals("txt")) {
            System.out.print("Server need return txt. Return text from file" + "\n");
            for (Object s : showFile()) {
                out.println(s);
            }

        } else if (inputLine.contains("login") && inputLine.length() > 5) {
            System.out.print("Server need result of checking login and password" + "\n");
            String login = "nurs";
            String password = "123";
            if (inputLine.contains(login) && inputLine.contains(password)) {
                out.println("Success");
            } else out.println("Failed");
        } else out.println(inputLine);
        System.out.println("----------------------------------------------");

        return out;
    }

    private List showFile() {
        List<String> listOfStringsFromFile = new ArrayList<>();
        String path = "C:\\Develop\\simple echo server with client\\src\\main\\resources\\file.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                listOfStringsFromFile.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfStringsFromFile;
    }
}