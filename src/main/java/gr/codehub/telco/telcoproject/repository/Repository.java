package gr.codehub.telco.telcoproject.repository;

import java.util.List;

public interface Repository<T, K> {

    T create(T t);
    T read(K id);
    List<T> read();
    boolean delete(K id);
    T update(T t);
}