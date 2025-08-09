package application;

import application.config.DatasourceConfig;
import application.controllers.UserHttpHandler;
import application.model.User;
import application.repositories.UserRepository;
import application.service.UserService;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Slf4j
public class Application {

   // private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {

        UserRepository userRepository = new UserRepository(DatasourceConfig.getConnection());
        UserService userService = new UserService(userRepository);

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        httpServer.createContext("/users",new UserHttpHandler(userService));
        httpServer.setExecutor(new ScheduledThreadPoolExecutor(10));
        httpServer.start();
        System.out.println("httpserver started");
    }
}