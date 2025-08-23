package dev.wisespirit.application;

import dev.wisespirit.application.config.DatasourceConfig;
import dev.wisespirit.application.controllers.UserHttpHandler;
import dev.wisespirit.application.repositories.UserRepository;
import dev.wisespirit.application.service.UserService;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);
    public static void main(String[] args) throws IOException {
        Configurator.setRootLevel(Level.INFO);
        UserRepository userRepository = new UserRepository(DatasourceConfig.getConnection());
        UserService userService = new UserService(userRepository);
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        httpServer.createContext("/users",new UserHttpHandler(userService));
        httpServer.setExecutor(new ScheduledThreadPoolExecutor(10));
        httpServer.start();
        log.info("Httpserver started");
    }
}