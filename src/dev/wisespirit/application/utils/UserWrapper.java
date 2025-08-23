package dev.wisespirit.application.utils;

import dev.wisespirit.application.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserWrapper {

    private UserWrapper(){
        throw new IllegalStateException("Utility class");
    }

    public static User toEntity(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }

    public static List<User> toEntityList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()){
            userList.add(toEntity(resultSet));
        }
        return userList;
    }
}
