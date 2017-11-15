package utils;

import Client.Client;
import Client.PortScanner;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Parser - the most ugly part of this project. It analyzes the args
 * and calls the appropriate functionality.
 */
public class Parser {

    /** client used for get http method and tcp communication */
    private Client client = new Client();
    /** hater of sout */
    private static final Logger LOGGER = Logger.getLogger( Parser.class.getName() );

    /**
     * Method for parsing the input parameter.
     * @param parsedExpr = args[]
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

            PortScanner portScanner = new PortScanner();
            portScanner.scanPortsAsync(parsedExpr[1], startPort, endPort);
            HashMap<Integer, String> hashMap =  portScanner.getPortsStatus(startPort);

            for (int i = startPort; i <= endPort; i++) {
                System.out.println(i + " : " + hashMap.get(i));
            }
        } else if (parsedExpr[2].equals("-s")) {
            LOGGER.info("Started scanning the ports.");
            String[] splitByLine = parsedExpr[parsedExpr.length-1].split("-");

            int startPort = Integer.valueOf(splitByLine[0]);
            int endPort = Integer.valueOf(splitByLine[1]);

            PortScanner portScanner = new PortScanner();
            portScanner.scanPortsAsync(parsedExpr[1], startPort, endPort);
            HashMap<Integer, String> hashMap =  portScanner.getPortsStatus(startPort);
        }
    }
}
