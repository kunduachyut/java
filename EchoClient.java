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
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // Send data to server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Receive data from server

            // Read user input and send it to the server
            System.out.println("Type a message (or type 'exit' to quit):");
            String message;
            while (!(message = userInput.readLine()).equalsIgnoreCase("exit")) { // Until 'exit' is typed
                output.println(message); // Send the message to the server
                String response = input.readLine(); // Read the echoed response
                System.out.println("Server echoed: " + response); // Display the echoed response
                System.out.println("Type another message (or 'exit' to quit):");
            }

            System.out.println("Closing connection.");
        } catch (IOException e) {
            System.err.println("Error connecting to Echo server: " + e.getMessage());
        }
    }
}
