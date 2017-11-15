
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App {

    public App() {

    }
    public static void main(String[] args) throws IOException, InterruptedException {
        App app = new App();
        app.run(args);
    }

    public void run(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(args[0], 80);
        socket.setSoTimeout(7000);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        sendMessage(out, new File(args[1]));
        readResponse(in);

        out.close();
        in.close();
    }

    private static void sendMessage2(BufferedWriter out, File request) throws IOException, InterruptedException {
        System.out.println(" * Request");

        String temp = "";
        for (String line : getContents(request)) {
            System.out.println(line);
            temp.concat(line);
//            out.write(line + "\r\n");
        }

        /*bidlocod*/
        for (int i = 0; i < temp.length(); i++) {
            out.write(temp.charAt(i));

            out.flush();
            Thread.sleep(10);
        }

        out.write("\r\n");
        out.flush();
    }

    private static void sendMessage(BufferedWriter out, File request) throws IOException {
        System.out.println(" * Request");

        for (String line : getContents(request)) {
            System.out.println(line);
            out.write(line + "\r\n");
        }

        out.write("\r\n");
        out.flush();
    }

    private static void readResponse(BufferedReader in) throws IOException {
        System.out.println("\n * Response");

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if (line.equals("0")) { break; }
        }
    }

    private static List<String> getContents(File file) throws IOException {
        List<String> contents = new ArrayList<String>();

        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;
        while ((line = input.readLine()) != null) {
            contents.add(line);
        }
        input.close();
        return contents;
    }
}