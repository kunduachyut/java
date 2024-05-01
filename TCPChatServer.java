package java;
import java.io.*;
import java.net.*;

public class TCPChatServer {
    public static void main(String[] args) {
        int port = 12345; // Port to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());

                // Create a new thread to handle this client
                new ClientHandler(socket).start();
            }

        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Inner class to handle individual client connections
    static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String clientMessage;

                // Read messages from the client and send responses
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Received from client: " + clientMessage);
                    // Echo the message back to the client
                    output.println("Server: " + clientMessage);
                }

                socket.close();

            } catch (IOException e) {
                System.err.println("Client connection error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
