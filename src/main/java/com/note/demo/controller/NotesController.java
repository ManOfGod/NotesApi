package com.note.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.note.demo.model.Note;
import com.note.demo.model.NoteRequest;
import com.note.demo.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NoteService service;

    @GetMapping
    public List<Note> getNotes() {
        return service.getNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNote(@PathVariable("id") long id) {
        return service.getNote(id);
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody NoteRequest noteRequest) {
        return service.createNote(noteRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable("id") long id, @RequestBody NoteRequest noteRequest) {
        return service.updateNote(id, noteRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") long id) {
        return service.deleteNote(id);
    }
}
