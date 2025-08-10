package application.controllers;

import application.model.User;
import application.service.UserService;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        switch (requestMethod){
            case "GET"-> doGet(exchange);
            case "POST"->doPost(exchange);
            case "DELETE"->doDelete(exchange);
            case "PUT"->doPut(exchange);
        }
    }

    private void doPut(HttpExchange exchange) {
    }

    private void doDelete(HttpExchange httpExchange){

    }

    private void doPost(HttpExchange exchange) {

    }

    private void doGet(HttpExchange exchange) throws SQLException {
        InputStream request = exchange.getRequestBody();
        OutputStream response = exchange.getResponseBody();
        List<User> users = userService.getAll();
        //objecttobytearray
        response.write();

    }
}
