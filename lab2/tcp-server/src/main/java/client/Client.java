package client;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client() {

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.run();
    }

    public void run() throws IOException, InterruptedException {

        String receivedMessage;

        /* Create new socket */
        Socket socket = new Socket("localhost",6789);

        /* Get the output of the Socket */
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter out = new BufferedWriter(osw);

        /* Use the writer for sending the header and the message */
        out.write("HTTP/1.0 200 OK\r\n");
        Thread.sleep(200);
        out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
        Thread.sleep(200);
        out.write("Server: Apache/0.8.4\r\n");
        Thread.sleep(200);
        /* bidlocodu care urmeaza e special */
//        while (true) {
//            boolean value = false;
//            if(value) {break;}
//        }

        out.write("Content-Type: text/html\r\n");
        out.write("Content-Length: 59\r\n");
        out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
        out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
        out.write("\r\n"); // The content starts afters this empty line
        out.write("<TITLE>Exemplu</TITLE>");
        out.write("<P>Acesta este un paragraph.</P>");

        out.newLine();
        out.flush();

        /* Read what comes from server */
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        receivedMessage = br.readLine();
        System.out.println("Message received from server : " + receivedMessage);

        /* Close the buffer writer */
        out.close();
    }
}
