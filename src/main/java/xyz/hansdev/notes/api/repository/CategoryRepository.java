package xyz.hansdev.notes.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import xyz.hansdev.notes.api.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Page<Category> findPaginated(Pageable pageable);

}
