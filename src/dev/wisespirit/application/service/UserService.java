package dev.wisespirit.application.service;

import dev.wisespirit.application.model.User;
import dev.wisespirit.application.model.dto.UserDto;
import dev.wisespirit.application.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserService implements AbstractService<User,UserDto,Integer>{
    private static final Logger log = LogManager.getLogger(UserService.class);
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(UserDto dto) throws SQLException {
        log.info("executing add() user:{}",dto);
        return repository.add(new User(dto.username(), dto.password()));
    }

    @Override
    public User update(Integer id, UserDto dto) throws SQLException {
        log.info("executing update() userId:{},user:{}",id,dto);
        return repository.update(id, new User(dto.username(),dto.password()));
    }

    @Override
    public void delete(Integer id) throws SQLException {
        log.info("executing delete() id:{}",id);
        repository.delete(id);
    }

    public List<User> getAll() throws SQLException {
        log.info("executing getAll():");
        return repository.getAll();
    }

    public User getById(int id) throws SQLException {
        log.info("executing getById() method userId :{} ",id);
        return repository.getById(id);
    }
}
