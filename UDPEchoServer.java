import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UDPEchoServer {
    private static final int PORT = 12345; 
    private static final int BUFFER_SIZE = 1024; 
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) { 
            System.out.println("UDP Echo server is running on port " + PORT);
            byte[] buffer = new byte[BUFFER_SIZE]; 
            while (true) { 
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Received from " + clientAddress + ":" + clientPort + " - " + receivedData);
                DatagramPacket sendPacket = new DatagramPacket(
                    receivePacket.getData(), 
                    receivePacket.getLength(), 
                    clientAddress, 
                    clientPort 
                );
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Error in UDP Echo server: " + e.getMessage());
        }
    }
}
