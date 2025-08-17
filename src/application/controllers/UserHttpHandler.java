package application.controllers;

import application.model.User;
import application.model.dto.UserDto;
import application.service.UserService;
import application.utils.GsonUtil;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class UserHttpHandler implements HttpHandler {

    private final UserService userService;

    public UserHttpHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpContext httpContext = exchange.getHttpContext();
        System.out.println(httpContext.getPath());
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
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        for (int i = 0; i < path.length; i++) {
            System.out.println("path index "+i+path[i]);
        }
        OutputStream responseBody = httpExchange.getResponseBody();
        if (path.length>=2){
            System.out.println("path 1 "+path[2]);
            int id = Integer.valueOf(path[2]);
            System.out.println(id);
            User user = userService.getById(id);
            System.out.println(user);
            String json = GsonUtil.objectToJson(user);
            responseBody.write(json.getBytes(StandardCharsets.UTF_8));
            httpExchange.getResponseHeaders().add("Content-Type","application/json");
            httpExchange.sendResponseHeaders(200,0);
            System.out.println("");
            responseBody.close();
            return;
        }
        httpExchange.sendResponseHeaders(404,0);
    }

    private void doPost(HttpExchange exchange) throws IOException, SQLException {
        InputStream requestBody = exchange.getRequestBody();
        UserDto user = GsonUtil.fromJsonToObject(requestBody, UserDto.class);
        User add = userService.add(user);
        exchange.sendResponseHeaders(201,0);
        exchange.getRequestHeaders().add("Content-Type","application/json");
        requestBody.close();
    }

    private void doGet(HttpExchange exchange) throws SQLException, IOException {
        InputStream request = exchange.getRequestBody();
        OutputStream response = exchange.getResponseBody();
        List<User> users = userService.getAll();
        exchange.sendResponseHeaders(200,0);
        exchange.getResponseHeaders().add("Content-Type","application/json");
        String json = GsonUtil.objectToJson(users);
        response.write(GsonUtil.objectToByteArray(json));
        response.close();
    }
}
