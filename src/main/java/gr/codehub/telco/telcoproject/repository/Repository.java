package gr.codehub.telco.telcoproject.repository;

import jakarta.transaction.Transactional;

import java.util.List;

public interface Repository <T, K>{
    T create(T t);
    T read(K id);
    List<T> read();
    boolean delete(K id);

}
