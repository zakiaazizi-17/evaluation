package ma.projet.dao;

import java.util.List;

public interface IDao<T, ID> {
    T create(T t);
    T update(T t);
    void delete(T t);
    T findById(ID id);
    List<T> findAll();
}