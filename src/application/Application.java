package application;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Application {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
      //  httpServer.createContext("/")
        httpServer.setExecutor(new ScheduledThreadPoolExecutor(10));
        httpServer.start();
    }
}