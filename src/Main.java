import Classes.DeviceAction;
import Classes.DeviceInfo;
import Classes.HouseholdDevice;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        List<HouseholdDevice> devices = Mocking.householdDevices;

        // Add endpoint for fetching all devices
        server.createContext("/api/getDiscDevices", new ServerController.GetDevicesHandler(devices));
        for (HouseholdDevice device : devices) {
            // Add GET endpoints for each device channel
            for (DeviceInfo info : device.getDeviceDataController().getDeviceData()) {
                server.createContext(info.getChannel().getChannelPath(), new ServerController.DeviceChannelHandler(info.getChannel().getChannelPath()));
            }
            // Add POST endpoints for each device action
            for (DeviceAction action : device.getDeviceActionController().getDeviceActions()) {
                server.createContext(action.getActionChannel().getChannelPath(), new ServerController.PostActionHandler(action.getDataChannel().getChannelPath()));
            }
        }

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }
}
