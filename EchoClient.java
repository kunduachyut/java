package java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private static final String SERVER_ADDRESS = "localhost"; 
    private static final int SERVER_PORT = 12345; 

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) { 
            System.out.println("Connected to Echo server");

            
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); 
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

            System.out.println("Type a message (or type 'exit' to quit):");
            String message;
            while (!(message = userInput.readLine()).equalsIgnoreCase("exit")) { 
                output.println(message); 
                String response = input.readLine();
                System.out.println("Server echoed: " + response); 
                System.out.println("Type another message (or 'exit' to quit):");
            }

            System.out.println("Closing connection.");
        } catch (IOException e) {
            System.err.println("Error connecting to Echo server: " + e.getMessage());
        }
    }
}
