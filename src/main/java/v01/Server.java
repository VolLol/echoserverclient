package v01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    public static void main(String[] args) {
        int portNumber = 2002;
        System.out.println("firstTry.Server start working");
        System.out.println("----------------------------------------------");

        try (ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;//что выйдет в строке со стороны сервера и что пришло от польователя
            Server server = new Server();
            while ((inputLine = in.readLine()) != null) {
                System.out.println("firstTry.Client write: " + inputLine);
                server.command(inputLine, out);

            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public PrintWriter command(String inputLine, PrintWriter out) {
        if (inputLine.equals("v")) {
            System.out.print("firstTry.Client ask about version. Return version for him" + "\n");
            out.println("version is 123456");
        } else if (inputLine.equals("txt")) {
            System.out.print("firstTry.Server need return txt. Return text from file" + "\n");
            out.println(showFile());
        } else if (inputLine.contains("login") && inputLine.length() > 5) {
            System.out.print("firstTry.Server need result of checking login and password" + "\n");
            String login = "nurs";
            String password = "123";
            if (inputLine.contains(login) && inputLine.contains(password)) {
                out.println("Success");
            } else out.println("Failed");
        } else out.println(inputLine);
        System.out.println("----------------------------------------------");

        return out;
    }

    private List<String> showFile() {
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