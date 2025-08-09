package application.repositories;


import java.sql.SQLException;

public interface AbstractRepository<T,ID> {
    T add(T t) throws SQLException;
    T getById(ID id) throws SQLException;
    void delete(ID id) throws SQLException;
    T update(ID id,T T) throws SQLException;
}
