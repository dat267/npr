package a2_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/* Run multiple instances of this class to test multithreading */
public class TCPClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 6789);
             DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in))) {
            String sentence;
            while (true) {
                System.out.print("Please enter a number or Ctrl+C to quit: ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                String modifiedSentence = inFromServer.readLine();
                System.out.println("From Server: " + modifiedSentence);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
