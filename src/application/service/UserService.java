package application.service;

import application.model.User;
import application.model.dto.UserDto;
import application.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements AbstractService<User,UserDto,Integer>{
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(UserDto dto) throws SQLException {
        return repository.add(new User(dto.username(), dto.username()));
    }

    @Override
    public User update(Integer id, UserDto dto) throws SQLException {
        return repository.update(id, new User(dto.username(),dto.password()));
    }

    @Override
    public void delete(Integer id) throws SQLException {
        repository.delete(id);
    }

    public List<User> getAll() throws SQLException {
        return repository.getAll();
    }

    public User getById(int id) throws SQLException {
        System.out.println("getbyid :    "+id);
        return repository.getById(id);
    }
}
