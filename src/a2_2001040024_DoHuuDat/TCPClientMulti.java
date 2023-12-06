package a2_2001040024_DoHuuDat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* This class try using multiple client threads */
public class TCPClientMulti {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try (Socket clientSocket = new Socket("localhost", 6789);
                     DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                     BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedReader ignored = new BufferedReader(new InputStreamReader(System.in))) {
                    String sentence;
                    sentence = Integer.toString(5);
                    outToServer.writeBytes(sentence + '\n');
                    String modifiedSentence = inFromServer.readLine();
                    System.out.println("From Server: " + modifiedSentence);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        executor.shutdown();
    }
}
