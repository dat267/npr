package UDPSocketThread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultithreadedUDPEchoServer {
    public static void main(String args[]) {
        int port = 9876;

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Create a new thread to handle the client
                new Thread(new UDPEchoServerThread(serverSocket, receivePacket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UDPEchoServerThread implements Runnable {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public UDPEchoServerThread(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Message from client: " + sentence);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            byte[] sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

