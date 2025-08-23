package dev.wisespirit.application.repositories;

import dev.wisespirit.application.config.DatasourceConfig;
import dev.wisespirit.application.model.Todo;

import java.sql.Connection;

public class TodoRepository implements AbstractRepository<Todo, Integer> {
    private final Connection connection;

    public TodoRepository() {
        this.connection = DatasourceConfig.getConnection();
    }

    @Override
    public Todo add(Todo todo) {
        return null;
    }

    @Override
    public Todo getById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Todo update(Integer id,Todo todo) {
        return null;
    }
}
