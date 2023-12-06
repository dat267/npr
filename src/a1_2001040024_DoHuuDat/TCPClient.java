package a1_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

/**
 * This class represents a TCP client that connects to a server and sends a number to the server. It receives the square
 * of the number from the server and prints it. The client continues to send numbers to the server until it is
 * interrupted (by pressing Ctrl+C).
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // Create a Socket and connect to the server at localhost on port 6789
        try (Socket clientSocket = new Socket("localhost", 6789)) {
            // Create a DataOutputStream to write to the server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            // Create a BufferedReader to read from the server's output stream
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Create a BufferedReader to read from the user's input
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            // Continuously read a number from the user
            String sentence;
            while (true) {
                try {
                    System.out.print("Please enter a number or Ctrl+C to quit: ");
                    sentence = inFromUser.readLine();
                    // Send the number to the server
                    outToServer.writeBytes(sentence + '\n');
                    // Read the square of the number from the server
                    String modifiedSentence = inFromServer.readLine();
                    // Print the square of the number
                    System.out.println("From Server: " + modifiedSentence);
                } catch (IOException e) {
                    // If there's an IOException, break the loop
                    break;
                }
            }
            // Close the client socket
            clientSocket.close();
        } catch (ConnectException e) {
            // If there's a ConnectException, print the stack trace
            System.out.println(e.getMessage());
        }
    }
}
