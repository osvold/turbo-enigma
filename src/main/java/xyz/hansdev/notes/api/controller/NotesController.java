package xyz.hansdev.notes.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import xyz.hansdev.notes.api.exception.ResourceNotFoundException;
import xyz.hansdev.notes.api.model.Note;
import xyz.hansdev.notes.api.service.NoteService;

import java.util.List;

@RestController
@RequestMapping(value = "/notes/")
public class NotesController {

    private NoteService noteService;

    public NotesController(NoteService noteService) { this.noteService = noteService; }

    @ResponseBody
    public List<Note> findPaginated(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {

        Page<Note> result = noteService.findPaginated(pageable);
        if(pageable.getPageNumber() > result.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return result.getContent();
    }

    @GetMapping(value = "{id}")
    public Note findOne(@PathVariable("id") Integer id) {
        return noteService.findOne(id);
    }

    @PostMapping
    public Note create(@PathVariable("note") Note note) {
        return noteService.create(note);
    }

    @PutMapping(value = "{id}")
    public Note update(@PathVariable("id") Integer id, @PathVariable("note") Note note) {
        return noteService.update(note);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        noteService.delete(id);
    }

}
