import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import Classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.io.OutputStream;
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
                List<HouseholdDevice> devices = createMockDevices();

                // Convert devices to JSON
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonResponse = gson.toJson(devices);

                // Send JSON response
                exchange.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

        private List<HouseholdDevice> createMockDevices() {
            List<HouseholdDevice> devices = new ArrayList<>();

            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "Room Light", "Living Room", "Light Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("light.png")));
            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "AC", "Bedroom", "AC Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("ac.png")));
            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "Thermostat", "Hallway", "Thermostat Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("thermostat.png")));
            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "Doorbell", "Front Door", "Doorbell Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("doorbell.png")));
            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "Smart TV", "Living Room", "Smart TV Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("tv.png")));
            devices.add(new HouseholdDevice(UUID.randomUUID().toString(), "Washing Machine", "Laundry Room", "Washing Machine Description", new DeviceDataController(), new DeviceActionController(), Utils.encodeImageToBase64("washing-machine.png")));

            return devices;
        }
    }
}