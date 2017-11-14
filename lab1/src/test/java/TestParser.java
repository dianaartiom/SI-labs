import org.junit.Test;
import utils.Parser;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class TestParser {

    private static final Logger LOGGER = Logger.getLogger( Parser.class.getName() );
    /* This method checks whether the input form is sutitable for scanPorts method */
    @Test
    public void parseString4ScanPorts() {
        String input = "-t agora.md -s 1-100";
        String[] string = input.split(" ");
        assertTrue(string[0].equals("-t"));
        assertTrue(string[1].equals("agora.md"));
        assertTrue(string[2].equals("-s"));
        String[] substring = string[3].split("-");
        assertTrue(substring[0].equals("1"));
        assertTrue(substring[1].equals("100"));
    }

    @Test
    public void parseString4PlainGet() {
        String input = "-t agora.md -get";
        String[] strings = input.split(" ");
        assertTrue(strings[0].equals("-t"));
        assertTrue(strings[1].equals("agora.md"));
        assertTrue(strings[2].equals("-get"));
    }

    @Test
    public void parseString4TCPMessage() {
        LOGGER.info("Checking the parse logic of TCPMessage input string.");
        String input = "-t localhost -p 8083 -m \"someme ssage\"";
        String[] full = input.split("\"");
        String[] strings = full[0].split(" ");
        String message = full[1];

        assertTrue(strings[0].equals("-t"));
        assertTrue(strings[1].equals("localhost"));
        assertTrue(strings[2].equals("-p"));
        assertTrue(strings[3].equals("8083"));
        assertTrue(strings[4].equals("-m"));

        assertTrue(strings.length == 5);

        assertTrue(message.equals("someme ssage"));
    }

    /* The following method checks the input string
     * for verbouseScanMethod
     */
    @Test
    public void parseString4VerbouseScan() {
        String input = "-t agora.md -s verbose 1-100 ";
        String[] string = input.split(" ");
        assertTrue(string[0].equals("-t"));
        assertTrue(string[1].equals("agora.md"));
        assertTrue(string[2].equals("-s"));
        assertTrue(string[3].equals("verbose"));
        String[] substring = string[4].split("-");
        assertTrue(substring[0].equals("1"));
        assertTrue(substring[1].equals("100"));
    }
}
