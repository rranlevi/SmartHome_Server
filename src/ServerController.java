import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import Classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.jshell.execution.Util;

import java.util.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ServerController {

    static class GetDevicesHandler implements HttpHandler {
        private final List<HouseholdDevice> devices;

        public GetDevicesHandler(List<HouseholdDevice> devices) {
            this.devices = devices;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Create mock devices

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

    static class DeviceChannelHandler implements HttpHandler {
        private final String channelPath;

        public DeviceChannelHandler(String channelPath) {
            this.channelPath = channelPath;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String responseContent = getDeviceValue(channelPath);
                sendResponse(exchange, responseContent);
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class PostActionHandler implements HttpHandler {
        private final String channelPath;

        public PostActionHandler(String channelPath) {
            this.channelPath = channelPath;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {

                // Read the request body
                InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                char[] buffer = new char[256];
                int read = reader.read(buffer);
                String newValue = new String(buffer, 0, read).trim();

                if (this.channelPath.equals("/camera/next_frame")) {
                    CameraStream cameraStream = null;

                    for (HouseholdDevice device : Mocking.householdDevices) {
                        for (DeviceAction action : device.getDeviceActionController().getDeviceActions()) {
                            if (action.getDataChannel().getChannelPath().equals(channelPath)) {
                                cameraStream = (CameraStream) action.getWidget();
                            }
                        }
                    }

                    if (cameraStream == null) {
                        return;
                    }
                    int frameNum = Integer.parseInt(cameraStream.getCurrentFrameNum());
                    String videoPath = "Camera_Streams/camera_stream.mp4";
                    newValue = Utils.processFrameFromVideo(frameNum, videoPath);
                }

                // Set the device value based on action
                boolean updated = setDeviceValue(channelPath, newValue);
                if (updated) {
                    sendResponse(exchange, String.format("{\"message\": \"%s set to %s\"}", channelPath, newValue));
                } else {
                    sendResponse(exchange, "{\"message\": \"Failed to set value\"}", 500);
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    private static String getDeviceValue(String channelPath) {
        for (HouseholdDevice device : Mocking.householdDevices) {
            for (DeviceInfo info : device.getDeviceDataController().getDeviceData()) {
                if (info.getChannel().getChannelPath().equals(channelPath)) {
                    return info.getDeviceInfo().getInfoValue();
                }
            }
        }
        return "{\"value\": \"Unknown\"}";
    }

    private static boolean setDeviceValue(String channelPath, String newValue) {
        for (HouseholdDevice device : Mocking.householdDevices) {
            for (DeviceInfo info : device.getDeviceDataController().getDeviceData()) {
                if (info.getChannel().getChannelPath().equals(channelPath)) {
                    info.getDeviceInfo().setInfoValue(newValue);
                    return true;
                }
            }
        }
        return false;
    }

    private static void sendResponse(HttpExchange exchange, String responseContent) throws IOException {
        sendResponse(exchange, responseContent, 200);
    }

    private static void sendResponse(HttpExchange exchange, String responseContent, int statusCode) throws IOException {
        byte[] responseBytes = responseContent.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}