package application;

import application.config.DatasourceConfig;
import application.controllers.UserHttpHandler;
import application.model.User;
import application.repositories.UserRepository;
import application.service.UserService;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
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