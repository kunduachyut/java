import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final int PORT = 12345; 

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { 
            System.out.println("Echo server is running on port " + PORT);

            while (true) { // Keep the server running indefinitely
                Socket clientSocket = serverSocket.accept(); // Accept a new client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a separate thread to handle each client
                new Thread(() -> {
                    try (BufferedReader input = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                        String line;
                        // Read from the client and echo back
                        while ((line = input.readLine()) != null) {
                            System.out.println("Received from client: " + line);
                            output.println(line); // Echo back to the client
                        }
                    } catch (IOException e) {
                        System.err.println("Error handling client: " + e.getMessage());
                    } finally {
                        try {
                            clientSocket.close(); // Ensure the connection is closed
                        } catch (IOException e) {
                            System.err.println("Error closing client socket: " + e.getMessage());
                        }
                    }
                }).start(); // Start the new thread to handle the client
            }
        } catch (IOException e) {
            System.err.println("Could not start Echo server: " + e.getMessage());
        }
    }
}
