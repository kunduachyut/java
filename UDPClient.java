package java;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        final int SERVER_PORT = 9876;
        final String SERVER_ADDRESS = "localhost"; // Change this to the server's IP address if needed

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            String message = "Hello from the client!";
            byte[] sendBuffer = message.getBytes();

            // Get the server address
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

            // Create a DatagramPacket to send the message
            DatagramPacket sendPacket = new DatagramPacket(
                sendBuffer,
                sendBuffer.length,
                serverAddress,
                SERVER_PORT
            );

            // Send the packet to the server
            clientSocket.send(sendPacket);

            System.out.println("Message sent to server: " + message);

            // Clean up
            clientSocket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
