package java;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 9876;

        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            byte[] receiveBuffer = new byte[1024];

            System.out.println("UDP Server listening on port " + PORT);

            while (true) {
                // Create a DatagramPacket to receive incoming messages
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                // Receive the incoming packet
                serverSocket.receive(receivePacket);

                // Get the message and sender information
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from client [" + clientAddress + ":" + clientPort + "]: " + message);
            }
        } catch (SocketException se) {
            System.err.println("Socket error: " + se.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
