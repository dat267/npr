package UDPSocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPEchoServer {
    public static void main(String args[]) throws Exception {
        int port = 9876;
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server is running...");
            // byte[] receiveData = new byte[1024];
            // byte[] sendData = new byte[1024];

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                sentence = sentence.trim();
                System.out.println("Message from client: " + sentence);

                InetAddress IPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
                serverSocket.send(sendPacket);

            }
        }
    }
}
