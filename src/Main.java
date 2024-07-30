import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Add endpoint for fetching all devices
        server.createContext("/api/getDiscDevices", new ServerController.GetDevicesHandler());

        // Add GET endpoints for each device channel
        server.createContext("/light/brightness", new ServerController.DeviceChannelHandler("/light/brightness"));
        server.createContext("/light/power", new ServerController.DeviceChannelHandler("/light/power"));
        server.createContext("/ac/temperature", new ServerController.DeviceChannelHandler("/ac/temperature"));
        server.createContext("/ac/power", new ServerController.DeviceChannelHandler("/ac/power"));
        server.createContext("/thermostat/current", new ServerController.DeviceChannelHandler("/thermostat/current"));
        server.createContext("/thermostat/target", new ServerController.DeviceChannelHandler("/thermostat/target"));
        server.createContext("/doorbell/status", new ServerController.DeviceChannelHandler("/doorbell/status"));
        server.createContext("/doorbell/last", new ServerController.DeviceChannelHandler("/doorbell/last"));
        server.createContext("/tv/volume", new ServerController.DeviceChannelHandler("/tv/volume"));
        server.createContext("/tv/channel", new ServerController.DeviceChannelHandler("/tv/channel"));
        server.createContext("/washingmachine/cycle", new ServerController.DeviceChannelHandler("/washingmachine/cycle"));
        server.createContext("/washingmachine/status", new ServerController.DeviceChannelHandler("/washingmachine/status"));

        // Add POST endpoints for each device action
        server.createContext("/light/on", new ServerController.PostActionHandler("/light/power"));
        server.createContext("/light/off", new ServerController.PostActionHandler("/light/power"));
        server.createContext("/ac/on", new ServerController.PostActionHandler("/ac/power"));
        server.createContext("/ac/off", new ServerController.PostActionHandler("/ac/power"));
        server.createContext("/ac/settemp", new ServerController.PostActionHandler("/ac/temperature"));
        server.createContext("/thermostat/increase", new ServerController.PostActionHandler("/thermostat/target"));
        server.createContext("/thermostat/decrease", new ServerController.PostActionHandler("/thermostat/target"));
        server.createContext("/thermostat/set", new ServerController.PostActionHandler("/thermostat/target"));
        server.createContext("/doorbell/ring", new ServerController.PostActionHandler("/doorbell/status"));
        server.createContext("/tv/on", new ServerController.PostActionHandler("/tv/channel"));
        server.createContext("/tv/off", new ServerController.PostActionHandler("/tv/channel"));
        server.createContext("/tv/setchannel", new ServerController.PostActionHandler("/tv/channel"));
        server.createContext("/washingmachine/start", new ServerController.PostActionHandler("/washingmachine/status"));
        server.createContext("/washingmachine/stop", new ServerController.PostActionHandler("/washingmachine/status"));
        server.createContext("/washingmachine/setcycle", new ServerController.PostActionHandler("/washingmachine/cycle"));

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }
}
