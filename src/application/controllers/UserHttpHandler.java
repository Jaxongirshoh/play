package application.controllers;

import application.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

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
        Headers headers = exchange.getRequestHeaders();
        headers.forEach((str,list)->{
            System.out.println(str);
            for (String s : list) {
                System.out.println(s);
            }
        });

    }

    private void doGet(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        headers.forEach((str,list)->{
            System.out.println(str);
            for (String s : list) {
                System.out.println(s);
            }
        });
    }
}
