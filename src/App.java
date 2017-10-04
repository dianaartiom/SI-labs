import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        String sentence = client.executeThroughSocket(80, "agora.md",
                                                        "GET / HTTP/1.1\nHost: agora.md\r\n\r\n");
        System.out.println(sentence);
    }
}
