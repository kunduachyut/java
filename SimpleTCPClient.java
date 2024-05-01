package java;

import java.io.*;
import java.net.*;

public class SimpleTCPClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // The server's hostname or IP address
        int port = 12345; // The port to connect to

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the server: " + hostname + ":" + port);

            // Input stream to receive data from the server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Read a message from the server
            String serverMessage = reader.readLine();
            System.out.println("Received from server: " + serverMessage);

            // Close the socket after communication
            socket.close();

        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
