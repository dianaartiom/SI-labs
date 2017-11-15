package server;

import client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
    }

    public void run() throws IOException {

        /* Server socket sta si asteapta :D */
        ServerSocket welcomeSocket = new ServerSocket(6789);

        /* Un cilcu infinit ca sa ruleze tot timpul*/
        while (true) {
            /* Deschide un socket cu cel care vrea sa se conecteze */
            Socket connectionSocket = welcomeSocket.accept();
            /* Citeste ce vine de la client */
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            /* Citeste tot ce vine de la client si printeaza */
            String s;
            while ((s = inFromClient.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty()) {
                    /* Daca nu mai vine nimic de la client - iesi din ciclu */
                    break;
                }
            }

            /* Pregateste socketul pentru a trimite la client */
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            /* Trimite clientului ceea ce ti-a transmis el */
            String clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            outToClient.writeBytes(clientSentence+ '\n');
            /* Inchide socketul */
            connectionSocket.close();
        }
    }
}

