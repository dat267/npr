package a3_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        int port = 9876;
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress IPAddress = InetAddress.getByName("localhost");
            while (true) {
                byte[] sendData;
                byte[] receiveData = new byte[1024];
                System.out.print("Please enter a real number: ");
                try {
                    double realNumber = Double.parseDouble(inFromUser.readLine());
                    sendData = Double.toString(realNumber).getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String receivedCubeValue = new String(receivePacket.getData()).trim();
                    System.out.println("Cube of the entered number received from server: " + receivedCubeValue);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid real number.");
                }
            }
        }
    }
}
