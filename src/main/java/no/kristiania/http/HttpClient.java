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

        String responseLine = readLine(socket);
        System.out.println(responseLine);
        String[] responseLineParts = responseLine.split(" ");
        responseCode = Integer.parseInt(responseLineParts[1]);
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder sb = new StringBuilder();

        int c;
        while((c = socket.getInputStream().read()) != -1) {
            if(c == '\n') {
                break;
            }
            sb.append((char) c);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
    new HttpClient("urlecho.appspot.com", 80, "/echo?body=Hello+World");
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseHeader(String headerName) {
        return null;
    }
}
