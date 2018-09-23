package xyz.hansdev.notes.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import xyz.hansdev.notes.api.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findPaginated(Pageable pageable);
}
