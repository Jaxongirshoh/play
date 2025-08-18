package application.controllers;

import application.model.User;
import application.model.dto.UserDto;
import application.service.UserService;
import application.utils.GsonUtil;
import com.sun.net.httpserver.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class UserHttpHandler implements HttpHandler {

    private static final Logger log = LogManager.getLogger(UserHttpHandler.class);
    private final UserService userService;

    public UserHttpHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        log.info("UserHttpHandler method  Path :{}",exchange.getRequestURI().getPath());
        String requestMethod = exchange.getRequestMethod();
        try {
            switch (requestMethod) {
                case "GET" -> doGet(exchange);
                case "POST" -> doPost(exchange);
                case "DELETE" -> doDelete(exchange);
                case "PUT" -> doPut(exchange);
            }
        }catch (Exception e){

        }
    }

    private void doPut(HttpExchange exchange) {

    }

    private void doDelete(HttpExchange httpExchange) throws SQLException, IOException {
        log.info("Delete method handler: {}, Path: {}",httpExchange.getRequestMethod(),httpExchange.getRequestURI().getPath());
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        OutputStream responseBody = httpExchange.getResponseBody();
        if (path.length>=2){
            int id = Integer.valueOf(path[2]);
            userService.delete(id);
            httpExchange.getResponseHeaders().add("Content-Type","application/json");
            httpExchange.sendResponseHeaders(204,0);
            responseBody.close();
            return;
        }
        httpExchange.sendResponseHeaders(404,0);
        httpExchange.close();
    }

    private void doPost(HttpExchange exchange) throws IOException, SQLException {
        log.info("Post method handler: {}, Path: {}",exchange.getRequestMethod(),exchange.getRequestURI().getPath());
        InputStream requestBody = exchange.getRequestBody();
        UserDto user = GsonUtil.fromJsonToObject(requestBody, UserDto.class);
        User add = userService.add(user);
        exchange.sendResponseHeaders(201,0);
        exchange.getRequestHeaders().add("Content-Type","application/json");
        requestBody.close();
    }

    private void doGet(HttpExchange exchange) throws SQLException, IOException {
        log.info("Get method handler: {} , Path : {}",exchange.getRequestMethod(),exchange.getRequestURI().getPath());
        OutputStream response = exchange.getResponseBody();
        List<User> users = userService.getAll();
        exchange.sendResponseHeaders(200,0);
        exchange.getResponseHeaders().add("Content-Type","application/json");
        String json = GsonUtil.objectToJson(users);
        response.write(GsonUtil.objectToByteArray(json));
        response.close();
    }
}
