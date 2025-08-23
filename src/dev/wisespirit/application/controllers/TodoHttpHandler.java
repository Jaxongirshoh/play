package dev.wisespirit.application.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.wisespirit.application.service.TodoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TodoHttpHandler implements HttpHandler {
    private static final Logger log = LogManager.getLogger(TodoHttpHandler.class);
    private final TodoService todoService;

    public TodoHttpHandler(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
}
