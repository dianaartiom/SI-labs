### Intro
In this laboratory work, my main task was to: 
1. build TCP and UDP server/client "programs" using sockets;
2. get an HTTP page (read RFC 2616);
3. have fun with some many more functionalities

### Work procedure
#### Tools and technologies
`Java` `Git` `Maven` `Junit`

##### Implementation logic: 
First of all, for the task of sending and receiving TCP messages, I have writtent the logic for a TCP server. It waits on the port 8083 for the client to connect:

Basic part:
```java
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
```
The clients sends(and receives) the TCP message as follows:
```java
/* This method opens a TCP Socket to a specified host, a specified port and sends a message there */
public void TCPMessage(String host, int port, String _message) throws IOException {
        /* Creates a socket */
        Socket socket = new Socket(host, port);
        PrintStream PS = new PrintStream(socket.getOutputStream());

        PS.println(_message);

        /* Prepares for receiving a message */
        InputStreamReader ir = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(ir);

        String message = br.readLine();
        System.out.println(message);
        
        /* Closses the connection */
        socket.close();
        LOGGER.info("Connection closed.");
    }
```

Other functionalities of the Client are to: 
1. get a page over HTTP
```java
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
```
where command is a string that looks like ``` GET / HTTP/1.1
                             Host: agora.md```

<br />
Port scanning was done in the following way: <br />

```java
/** asynchronously scans the ports in range startingPort - endingPort */
    public static void scanPortsAsync(String host, int startingPort, int endingPort) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int port = startingPort; port <= endingPort; port++) {
            futures.add(ScannerHelper.portIsOpen(es, host, port, timeout));
        }
        es.shutdown();
    }

    /**
     *
     * @param startingPort - starting point for port scanning
     * @return - returns the futures list containing the port and it's status(open/closed)
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static HashMap<Integer, String> getPortsStatus(int startingPort)
            throws ExecutionException, InterruptedException {

        int openPorts = 0, i = startingPort;
        HashMap<Integer, String> hashMap = new HashMap<>();

        for (final Future<Boolean> f : futures) {
            if (f.get()) {
                hashMap.put(i, "open");
                openPorts++;
            } else {
                hashMap.put(i, "closed");
            }
            i++;
        }

        System.out.println("There are " + openPorts + " (probed with a timeout of " + timeout + "ms).");
        return hashMap;
    }
```
Next follows the instructions on what input gives what results. Attention: The program works only for valid input! :sob: <br/>

#### In order to send a TCP message one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t localhost -s 8083 -m This is the message."
```

#### In order to scan the ports one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -s verbose 1-100"
```


#### In order to do a verbose scan the ports one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -s verbose 1-100"
```

#### In order to do a get request one would use the following command:
```
mvn exec:java -Dexec.mainClass=App -Dexec.args="-t agora.md -get"
```


P.S. Sorry for the code smells. Perfectiunea e asimptotica.))
