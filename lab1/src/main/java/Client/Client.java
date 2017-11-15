package Client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Client {

    public Client() { }

    public void TCPMessage(String host, int port, String _message) throws IOException {
        Socket socket = new Socket(host, port);
        PrintStream PS = new PrintStream(socket.getOutputStream());

        PS.println(_message);

        InputStreamReader ir = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(ir);

        String message = br.readLine();
        System.out.println(message);

        socket.close();
        LOGGER.info("Connection closed.");
    }

    /* Functionality for get */

    public static void get(int port, String host, String command) throws IOException, InterruptedException {
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(7000);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        sendMessage(out, command);
        readResponse(in);

        out.close();
        in.close();
    }

    private static void sendMessage(BufferedWriter out, String command) throws IOException {
        System.out.println(" * Request");

        out.write(command + "\r\n\r\n");
        out.flush();
    }

    private static void readResponse(BufferedReader in) throws IOException {
        System.out.println("\n * Response");

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.equals("0")) { break; }
        }
    }
}
