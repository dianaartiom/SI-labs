package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        while (true) {
            server.run();
        }
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8083);

        Socket socket = serverSocket.accept();

        InputStreamReader IR = new InputStreamReader(socket.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String message = BR.readLine();
        System.out.println(message);

        if (message != null) {
            PrintStream PS = new PrintStream(socket.getOutputStream());
            PS.println("Message received. ");
        }
        socket.close();
}
    public Server() throws IOException {
    }
}
