package Client;

import utils.ScannerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* I responsibly declare that I got inspired from this source -
     * https://stackoverflow.com/questions/11547082/fastest-way-to-scan-ports-with-java*/
public class PortScanner {

    private static final int timeout = 300;
    private static List<Future<Boolean>> futures;

    public PortScanner() {
        this.futures = new ArrayList<>();
    }

    public static void scanPortsAsync(String host, int startingPort, int endingPort) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int port = startingPort; port <= endingPort; port++) {
            futures.add(ScannerHelper.portIsOpen(es, host, port, timeout));
        }
        es.shutdown();
    }

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
