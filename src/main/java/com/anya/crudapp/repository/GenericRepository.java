package com.anya.crudapp.repository;
import java.util.List;

public interface GenericRepository<T, ID> {

    T get(ID id);

    T update(T object);

    T delete(ID id);

    T insert(T object);

    ID searchByName(String name);

    List<T> getAll();


}
