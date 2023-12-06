package a3_2001040024_DoHuuDat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPCubeServer {
    public static void main(String[] args) throws Exception {
        int port = 9876;
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server is running...");
            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData;
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                double receivedNumber = Double.parseDouble(new String(receivePacket.getData()).trim());
                double cubeValue = Math.pow(receivedNumber, 3);
                InetAddress IPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = Double.toString(cubeValue).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        }
    }
}
