package application.repositories;



public interface AbstractRepository<T,ID> {
    T add(T t);
    T getById(ID id);
    void delete(ID id);
    T update(ID id);
}
