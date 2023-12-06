package a2_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

/* Modified TCPServer with multithreading capability */
public class TCPServerMultithread {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Server is running and waiting for a client...");
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                new ClientHandler(connectionSocket).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket connectionSocket;

        public ClientHandler(Socket connectionSocket) {
            this.connectionSocket = connectionSocket;
        }

        @Override
        public void run() {
            try (BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                 DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream())) {
                String clientSentence;
                while ((clientSentence = inFromClient.readLine()) != null) {
                    System.out.println("Received from Client: " + clientSentence);
                    try {
                        BigDecimal number = new BigDecimal(clientSentence);
                        BigDecimal square = number.multiply(number);
                        outToClient.writeBytes(square.toString() + '\n');
                    } catch (NumberFormatException e) {
                        outToClient.writeBytes("Invalid input. Please enter a valid decimal number.\n");
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
