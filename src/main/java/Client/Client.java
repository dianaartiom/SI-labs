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

    public static String get(int portNo, String portAddress, String command) throws IOException {

        StringBuilder responseString = new StringBuilder();
        PrintWriter writer = null;
        BufferedReader bufferedReader = null;
        Socket clientSocket = null;

        try {
            clientSocket = new Socket(portAddress, portNo);
            if (!clientSocket.isConnected())
                throw new SocketException("Could not connect to Socket");

            clientSocket.setKeepAlive(true);

            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(command);
            writer.flush();

            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String str;

            while (!(str = bufferedReader.readLine()).equals(null)) {
                responseString.append(bufferedReader.readLine());
                System.out.println(str);
                if (str.equals("0")) {
                    break;
                }
            }

        } finally {
            if (writer != null)
                writer.close();
            if (bufferedReader != null)
                bufferedReader.close();
            if (clientSocket != null)
                clientSocket.close();
        }

        return responseString.toString();
    }
}
