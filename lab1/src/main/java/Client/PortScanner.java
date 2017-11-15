package Client;

import utils.ScannerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** I responsibly declare that I got inspired from this source -
 * https://stackoverflow.com/questions/11547082/fastest-way-to-scan-ports-with-java
 * PortScanner class asyncrhonously scans the ports of a specified host
 * and returns the host of each of them (in the given specified range).
 * */

public class PortScanner {

    /** timeout for */
    private static final int timeout = 300;
    /** list of future results of the requests*/
    private static List<Future<Boolean>> futures;

    /**
     * Empty constructor.
     */
    public PortScanner() {
        this.futures = new ArrayList<>();
    }

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
}
