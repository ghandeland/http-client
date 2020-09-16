package no.kristiania.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {
    @Test
    void shouldReturnSuccessfulStatusCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo");

        assertEquals(200, client.getResponseCode());
    }

    @Test
    void shouldReturnUnsuccessfulStatusCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?status=404");

        assertEquals(404, client.getResponseCode());
    }

    @Test
    void shouldReadResponseHeader() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?body=Hello");

        assertEquals("5", client.getResponseHeader("Content-Length"));
    }
}