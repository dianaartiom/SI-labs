package utils;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Used to check whether the ports are open/closed.
 * Helper class for the PortScanner class.
 * I responsibly declare that I have used the following source -
 * https://stackoverflow.com/questions/11547082/fastest-way-to-scan-ports-with-java
 * */

public class ScannerHelper {

    /**
     * @param es - Executor service for executing asynchronoulsly the futures
     * @param ip - host address
     * @param port - port on the host
     * @param timeout - life timeout
     * @return
     */
    public static Future<Boolean> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
        return es.submit(new Callable<Boolean>() {
            @Override public Boolean call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }
}
