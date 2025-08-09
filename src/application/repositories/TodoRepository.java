package application.repositories;

import application.config.DatasourceConfig;
import application.model.Todo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
