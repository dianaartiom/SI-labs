import utils.Parser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Running main class. Used for starting the application.
 */
public class App {
    private static Parser parser = new Parser();
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        parser.parse(args);
    }
}
