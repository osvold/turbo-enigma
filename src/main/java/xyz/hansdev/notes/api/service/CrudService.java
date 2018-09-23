package xyz.hansdev.notes.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, V> {
    Page<T> findPaginated(Pageable pageable);
    T findOne(V v);
    T create(T t);
    T update(T t);
    void delete(V v);
}
