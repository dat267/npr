package a1_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents a TCP server that listens on a specified port and accepts connections from clients. It reads a
 * decimal number from the client, calculates its square, and sends the result back to the client. If the input is not a
 * valid decimal number, it sends an error message to the client.
 */
public class TCPServer {
    public static void main(String[] args) {
        // Create a ServerSocket and bind it to a specific port
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Server is running and waiting for a client...");
            // Continuously accept connections from clients
            while (true) {
                // Accept a connection from a client
                Socket connectionSocket = serverSocket.accept();
                // Create a BufferedReader to read from the client's input stream
                BufferedReader
                        inFromClient
                        = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                // Create a DataOutputStream to write to the client's output stream
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                // Continuously read lines from the client
                String clientSentence;
                while ((clientSentence = inFromClient.readLine()) != null) {
                    System.out.println("Received from Client: " + clientSentence);
                    // Try to convert the received string to a BigDecimal
                    try {
                        BigDecimal number = new BigDecimal(clientSentence);
                        // Calculate the square of the BigDecimal
                        BigDecimal square = number.multiply(number);
                        // Send the square of the number back to the client
                        outToClient.writeBytes(square.toString() + '\n');
                    } catch (NumberFormatException e) {
                        // If the input is not a valid decimal number, send an error message
                        outToClient.writeBytes("Invalid input. Please enter a valid decimal number.\n");
                    }
                }
            }
        } catch (IOException e) {
            // If there's an IOException, print the stack trace
            System.out.println(e.getMessage());
        }
    }
}
