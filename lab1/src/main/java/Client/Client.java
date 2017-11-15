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

/**
 * Client class is used for sending a TCP message to a server.
 * Also, it embeds the functionalities for sending an HTTP command to a host.
 */
public class Client {

    /**
     * Empty constructor
     */
    public Client() { }

    /**
     *
     * @param host - address of the host being intended to connect to and stend a TCP message
     * @param port - port of the host machine
     * @param _message - a message to be sent to the server
     * @throws IOException
     */
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

    /**
     *
     * @param port- port of the host machine
     * @param host - address of the host being intended to connect to and stend a TCP message
     * @param command - an http method, containing the url as well as http v
     * @throws IOException
     * @throws InterruptedException
     */
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

    /**
     * Method used to write to the server
     * @param out - buffer writer, used to be sent to the server
     * @param command - an http method, containing the url as well as http v
     * @throws IOException
     */
    private static void sendMessage(BufferedWriter out, String command) throws IOException {
        System.out.println(" * Request");

        out.write(command + "\r\n\r\n");
        out.flush();
    }

    /**
     * Method used to read the response from server
     * @param in - input buffer reader, attached to the connection socket
     * @throws IOException
     */
    private static void readResponse(BufferedReader in) throws IOException {
        System.out.println("\n * Response");

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.equals("0")) { break; }
        }
    }
}
