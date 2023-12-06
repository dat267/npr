package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastPublisher {

    public static void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
        socket.send(packet);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        int n = 1;
        while (true) {
            String s = "Hi there! This is a multicast message number ";

            s = s + Integer.toString(n) + " from publisher!";
            n++;
            sendUDPMessage(s, "230.0.0.0", 4321);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // sendUDPMessage("This is the second multicast message","230.0.0.0", 4321);
            // sendUDPMessage("This is the third multicast message","230.0.0.0", 4321);
            // sendUDPMessage("OFF", "230.0.0.0", 4321);

        }
    }
}
