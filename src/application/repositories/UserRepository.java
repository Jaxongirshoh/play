package application.repositories;

import application.config.DatasourceConfig;
import application.model.User;
import application.utils.UserWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Slf4j
public class UserRepository implements AbstractRepository<User,Integer>  {

    private static final Logger log = LogManager.getLogger(UserRepository.class);
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User add(User user) throws SQLException {
        log.info("executing add user:{}",user);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(username,password) values(?,?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        log.info("add() executing query insert into users(username,password) values({},{})",user.getUsername(),user.getPassword());
        preparedStatement.executeQuery();
        return user;
    }

    @Override
    public User getById(Integer id) throws SQLException {
        log.info("executing getById userId: {}",id);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setInt(1,id);
        log.info("getById() executing query select * from users where id = {}",id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return UserWrapper.toEntity(resultSet);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        log.info("executing delete userId: {}",id);
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
        preparedStatement.setInt(1,id);
        log.info("delete() executing query delete from users where id = {}",id);
        preparedStatement.execute();
    }

    @Override
    public User update(Integer id,User user) throws SQLException {
        log.info("executing update id :{}, user:{}",id,user);
        PreparedStatement preparedStatement = connection.prepareStatement("update users set username = ? ,password = ? where id = ?");
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3,id);
        log.info("update() executing query update users set username = {} ,password = {} where id = {}",user.getUsername(),user.getPassword(),id);
        preparedStatement.executeUpdate();
        /*PreparedStatement psm = connection.prepareStatement("select * from users where id = ?");
        ResultSet resultSet = psm.executeQuery();
        return UserWrapper.toEntity(resultSet);*/
        return user;
    }

    public List<User> getAll() throws SQLException {
        log.info("executing getAll:");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        log.info("getAll() executing query select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        return UserWrapper.toEntityList(resultSet);
    }
}
