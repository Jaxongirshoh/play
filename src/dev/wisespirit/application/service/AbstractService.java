package dev.wisespirit.application.service;

import java.sql.SQLException;
import java.util.List;

public interface AbstractService<T,DTO,ID> {
    T add(DTO t) throws SQLException;
    T update(ID id,DTO t) throws SQLException;
    void delete(ID id)throws SQLException;
    List<T> getAll()throws SQLException;
    T getById(ID id)throws SQLException;

}
