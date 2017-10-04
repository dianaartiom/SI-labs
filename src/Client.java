import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    public Client() {

    }

    public static String executeThroughSocket(int portNo, String portAddress, String command) throws IOException {

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

            while ((str = bufferedReader.readLine()) != null) {
                responseString.append(bufferedReader.readLine());
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
