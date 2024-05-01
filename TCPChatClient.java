package java;
import java.io.*;
import java.net.*;

public class TCPChatClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // The server's hostname or IP address
        int port = 12345; // The port to connect to

        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");

            // Start threads for reading and writing
            new Thread(new ReadTask(socket)).start();
            new Thread(new WriteTask(socket)).start();

        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Reading task for receiving messages from the server
    static class ReadTask implements Runnable {
        private Socket socket;

        public ReadTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
                String serverMessage;

                // Continuously read messages from the server
                while ((serverMessage = input.readLine()) != null) {
                    System.out.println("Received from server: " + serverMessage);
                }

                socket.close();

            } catch (IOException e) {
                System.err.println("Read error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Writing task for sending messages to the server
    static class WriteTask implements Runnable {
        private Socket socket;

        public WriteTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String clientMessage;

                // Continuously read from console and send to server
                while ((clientMessage = console.readLine()) != null) {
                    output.println(clientMessage);
                }

                socket.close();

            } catch (IOException e) {
                System.err.println("Write error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
