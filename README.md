### LABS 1 SI
#### Technologies
`Java` `Git` `Maven` `Junit`

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

Basic part:
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
P.S. Sorry for the code smells. Perfectiunea e asimptotica.))
