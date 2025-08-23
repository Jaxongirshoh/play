package dev.wisespirit.application.controllers;

import dev.wisespirit.application.model.User;
import dev.wisespirit.application.model.dto.UserDto;
import dev.wisespirit.application.service.UserService;
import dev.wisespirit.application.utils.GsonUtil;
import com.sun.net.httpserver.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        log.info("UserHttpHandler method  Path :{}", exchange.getRequestURI().getPath());
        String requestMethod = exchange.getRequestMethod();
        try {
            switch (requestMethod) {
                case "GET" -> doGet(exchange);
                case "POST" -> doPost(exchange);
                case "DELETE" -> doDelete(exchange);
                case "PUT" -> doPut(exchange);
            }
        } catch (SQLException e) {
            log.error("SQLException occurred :{}",e.getMessage());
        } catch (IOException e) {
            log.error("IOException occurred :{}",e.getMessage());
        }
    }

    private void doPut(HttpExchange exchange) throws SQLException, IOException {
        log.info("Put method handler: {}, Path: {}", exchange.getRequestMethod(), exchange.getRequestURI().getPath());
        String[] path = exchange.getRequestURI().getPath().split("/");
        InputStream requestBody = exchange.getRequestBody();
        OutputStream responseBody = exchange.getResponseBody();
        if (path.length >= 2) {
            Integer id = Integer.valueOf(path[2]);
            UserDto userDto = GsonUtil.fromJsonToObject(requestBody, UserDto.class);
            User update = userService.update(id, userDto);
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            responseBody.write(GsonUtil.objectToByteArray(GsonUtil.objectToJson(update)));
            responseBody.close();
            return;
        }
        exchange.sendResponseHeaders(400, 0);
        responseBody.close();
    }

    private void doDelete(HttpExchange httpExchange) throws SQLException, IOException {
        log.info("Delete method handler: {}, Path: {}", httpExchange.getRequestMethod(), httpExchange.getRequestURI().getPath());
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        OutputStream responseBody = httpExchange.getResponseBody();
        if (path.length >= 2) {
            int id = Integer.valueOf(path[2]);
            userService.delete(id);
            httpExchange.sendResponseHeaders(204, 0);
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            responseBody.close();
            return;
        }
        httpExchange.sendResponseHeaders(404, 0);
        responseBody.close();
    }

    private void doPost(HttpExchange exchange) throws IOException, SQLException {
        log.info("Post method handler: {}, Path: {}", exchange.getRequestMethod(), exchange.getRequestURI().getPath());
        InputStream requestBody = exchange.getRequestBody();
        OutputStream responseBody = exchange.getResponseBody();
        UserDto user = GsonUtil.fromJsonToObject(requestBody, UserDto.class);
        System.out.println(user);
        User add = userService.add(user);
        exchange.sendResponseHeaders(201, 0);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        responseBody.write(GsonUtil.objectToByteArray(GsonUtil.objectToJson(add)));
        responseBody.close();
    }

    private void doGet(HttpExchange exchange) throws SQLException, IOException {
        log.info("Get method handler: {} , Path : {}", exchange.getRequestMethod(), exchange.getRequestURI().getPath());
        String[] path = exchange.getRequestURI().getPath().split("/");
        OutputStream response = exchange.getResponseBody();
        if (path.length >2) {
            Integer id = Integer.valueOf(path[2]);
            User user = userService.getById(id);
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            String json = GsonUtil.objectToJson(user);
            response.write(GsonUtil.objectToByteArray(json));
            response.close();
            return;
        }
        List<User> users = userService.getAll();
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        String json = GsonUtil.objectToJson(users);
        response.write(GsonUtil.objectToByteArray(json));
        response.close();
    }
}
