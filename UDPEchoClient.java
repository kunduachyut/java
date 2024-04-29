package java;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPEchoClient {
    private static final String SERVER_ADDRESS = "localhost"; 
    private static final int SERVER_PORT = 12345; 
    private static final int BUFFER_SIZE = 1024; 
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) { 
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS); 
            System.out.println("Connected to UDP Echo server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
            byte[] sendData = "Hello, Echo Server!".getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                serverAddress,
                SERVER_PORT
            );
            clientSocket.send(sendPacket);
            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);
            String echoedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server echoed: " + echoedData);

        } catch (Exception e) {
            System.err.println("Error in UDP Echo client: " + e.getMessage());
        }
    }
}
