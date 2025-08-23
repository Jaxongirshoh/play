package dev.wisespirit.application.service;

import dev.wisespirit.application.model.Todo;
import dev.wisespirit.application.model.dto.TodoDto;
import dev.wisespirit.application.repositories.TodoRepository;

import java.sql.SQLException;
import java.util.List;

public class TodoService implements AbstractService<Todo, TodoDto,Integer>{
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo add(TodoDto t) throws SQLException {
        return null;
    }

    @Override
    public Todo update(Integer integer, TodoDto t) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }

    @Override
    public List<Todo> getAll(){
        return null;
    }

    @Override
    public Todo getById(Integer integer) {
        return null;
    }
}
