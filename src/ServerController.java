import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import Classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerController {

    static class PostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String response = "{\"message\": \"Hello, this is a POST response!\"}";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = "{\"message\": \"Hello, this is a GET response!\"}";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class GetDevicesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Create mock devices
                List<HouseholdDevice> devices = Mocking.createMockDevices();

                // Convert devices to JSON
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonResponse = gson.toJson(devices);

                // Calculate content length
                byte[] jsonResponseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

                // Send JSON response
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, jsonResponseBytes.length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponseBytes);
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}