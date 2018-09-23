package xyz.hansdev.notes.api.service.implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.Note;
import xyz.hansdev.notes.api.repository.NoteRepository;
import xyz.hansdev.notes.api.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Page<Note> findPaginated(Pageable pageable) {
        return noteRepository.findPaginated(pageable);
    }

    @Override
    public Note findOne(Integer id) {
        return noteRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Note create(Note note) { return noteRepository.save(note); }

    @Override
    public Note update(Note note) {
        Note original = noteRepository.findById(note.getId()).orElseThrow(ResourceNotFoundException::new);
        original.setTitle(note.getTitle());
        original.setBody(note.getBody());
        original.setCategory(note.getCategory());
        return noteRepository.save(original);
    }

    @Override
    public void delete(Integer id) {
        Note note = noteRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        noteRepository.delete(note);
    }
}
