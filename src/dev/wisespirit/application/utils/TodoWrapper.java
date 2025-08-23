package dev.wisespirit.application.utils;

import dev.wisespirit.application.enums.Status;
import dev.wisespirit.application.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TodoWrapper {

    private TodoWrapper(){
        throw new IllegalStateException("Utility class");
    }

    public static Todo toEntity(ResultSet resultSet) throws SQLException {
        return new Todo(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("task"),
                resultSet.getString("description"),
                resultSet.getDate("created_at").toLocalDate().atStartOfDay(),
                Status.valueOf(resultSet.getString("status"))

        );
    }

    public static List<Todo> toEntityList(ResultSet resultSet) throws SQLException {
        List<Todo> todoList = new ArrayList<>();
        while (resultSet.next()){
            todoList.add(toEntity(resultSet));
        }
        return todoList;
    }

}
