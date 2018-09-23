package xyz.hansdev.notes.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import xyz.hansdev.notes.api.model.Note;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    Page<Note> findPaginated(Pageable pageable);
}
