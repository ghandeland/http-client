package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClient {
    private int responseCode = 200;

    public HttpClient(final String host, int port, final String requestTarget) throws IOException {

        String request = "GET " + requestTarget + " HTTP/1.1\r\n"
                + "Host: " + host + "\r\n"
                + "\r\n";

        Socket socket = new Socket(host, port);

        socket.getOutputStream().write(request.getBytes());

        int c;
        while((c = socket.getInputStream().read()) != -1) {
            if(c == '\n') {
                break;
            }
            System.out.print((char) c);
        }
    }

    public static void main(String[] args) throws IOException {
    new HttpClient("urlecho.appspot.com", 80, "/echo?body=Hello+World");
    }

    public int getResponseCode() {
        return responseCode;
    }
}
