package application.repositories;

import application.config.DatasourceConfig;
import application.model.User;
import application.utils.UserWrapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserRepository implements AbstractRepository<User,Integer>  {

    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User add(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(username,password) values(?,?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeQuery();
        return user;
    }

    @Override
    public User getById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users u where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return UserWrapper.toEntity(resultSet);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    @Override
    public User update(Integer id,User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set username = ? ,password = ? where id = ?");
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3,id);
        preparedStatement.executeUpdate();
        PreparedStatement psm = connection.prepareStatement("select * from users where id = ?");
        ResultSet resultSet = psm.executeQuery();
        return UserWrapper.toEntity(resultSet);
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        return UserWrapper.toEntityList(resultSet);
    }
}
