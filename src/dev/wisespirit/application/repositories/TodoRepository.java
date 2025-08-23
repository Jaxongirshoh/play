package dev.wisespirit.application.repositories;

import dev.wisespirit.application.config.DatasourceConfig;
import dev.wisespirit.application.model.Todo;
import dev.wisespirit.application.utils.TodoWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TodoRepository implements AbstractRepository<Todo, Integer> {
    private static final Logger log = LogManager.getLogger(TodoRepository.class);
    private final Connection connection;

    public TodoRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Todo add(Todo todo) throws SQLException {
        log.info("executing add() todo:{} ",todo);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into todos(user_id,task,description,created_at,status) values(?,?,?,?,?)");
        preparedStatement.setInt(1,todo.getUserId());
        preparedStatement.setString(2,todo.getTask());
        preparedStatement.setString(3,todo.getDescription());
        preparedStatement.setDate(4,Date.valueOf(todo.getCreatedAt().toLocalDate()));
        preparedStatement.setString(5,todo.getStatus().toString());
        preparedStatement.execute();
        return todo;
    }

    @Override
    public Todo getById(Integer id) throws SQLException {
        log.info("executing getById() todoId:{} ",id);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from todos where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next()? TodoWrapper.toEntity(resultSet):null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        log.info("executing delete() todoId:{}",id);
        PreparedStatement preparedStatement = connection.prepareStatement("delete from todos where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    @Override
    public Todo update(Integer id,Todo todo) throws SQLException {
        log.info("executing update() todoId:{},todo:{}",id,todo);
        PreparedStatement preparedStatement = connection.prepareStatement("update todos set user_id=?,task=?,description=?,created_at=?,status=? where id=?");
        preparedStatement.setInt(1,todo.getUserId());
        preparedStatement.setString(2, todo.getTask());
        preparedStatement.setString(3,todo.getDescription());
        preparedStatement.setDate(4,Date.valueOf(todo.getCreatedAt().toLocalDate()));
        preparedStatement.setString(5,todo.getStatus().toString());
        preparedStatement.setInt(6,id);
        preparedStatement.executeUpdate();
        return todo;
    }

    public List<Todo> getAll() throws SQLException {
        log.info("executing getAll():");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from todos");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next()?TodoWrapper.toEntityList(resultSet):null;
    }
}
