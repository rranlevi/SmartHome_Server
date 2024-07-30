import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/postPath", new ServerController.PostHandler());
        server.createContext("/api/getPath", new ServerController.GetHandler());
        server.createContext("/api/getDiscDevices", new ServerController.GetDevicesHandler()); // Disc is discoverable
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }
}
