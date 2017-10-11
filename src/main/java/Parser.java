import Client.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Parser {

    private Client client = new Client();
    private static final Logger LOGGER = Logger.getLogger( Parser.class.getName() );

    public void parse(String[] parsedExpr) throws IOException, ExecutionException, InterruptedException {
        System.out.println(parsedExpr);
        if (parsedExpr[2].equals("-p")) {
            String string = "";
            for (int i = 5; i < parsedExpr.length; i++) {
                string +=  parsedExpr[i] + " ";
            }
            client.TCPMessage(parsedExpr[1], Integer.valueOf(parsedExpr[3]), string);
        }  else if (parsedExpr[2].equals("-get")) {
            Client.get(80, parsedExpr[1], "GET / HTTP/1.1\nHost: agora.md\r\n\r\n");
        } else if (parsedExpr[3].equals("verbose")) {
            String[] splitByLine = parsedExpr[parsedExpr.length-1].split("-");
            int startPort = Integer.valueOf(splitByLine[0]);
            int endPort = Integer.valueOf(splitByLine[1]);
            HashMap<Integer, String> hashMap = client.scanPorts(parsedExpr[1], startPort, endPort);

            for (int i = startPort; i <= endPort; i++) {
                System.out.println(i + " : " + hashMap.get(i));
            }
        } else if (parsedExpr[2].equals("-s")) {
            LOGGER.info("Started scanning the ports.");
            String[] splitByLine = parsedExpr[parsedExpr.length-1].split("-");
            client.scanPorts(parsedExpr[1], Integer.valueOf(splitByLine[0]), Integer.valueOf(splitByLine[1]));
        }
    }
}
