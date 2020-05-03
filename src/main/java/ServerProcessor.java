import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerProcessor {

    public void execute(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client write: " + inputLine);
                List<String> listFromFile = null;
                if (inputLine.equals("v")) out.println(showVersion());
                else if (inputLine.equals("txt")) {
                    listFromFile = showFile();
                    String response;
                    for (int i = 0; i < listFromFile.size(); i++) {
                        response = listFromFile.get(i);
                        out.println(response);
                    }
                    out.flush();

                } else if (inputLine.contains("login") && inputLine.length() > 5)
                    out.println(checkLogin(inputLine));
                else out.println(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String showVersion() {
        return "Version is 23456";
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

    private String checkLogin(String inputLine) {
        System.out.print("Server return result of checking login and password" + "\n");
        String login = "nurs";
        String password = "123";
        String message;
        if (inputLine.contains(login) && inputLine.contains(password)) {
            message = "Success";
        } else message = "Failed";
        return message;
    }
}
