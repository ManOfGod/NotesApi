package com.note.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.note.demo.model.Note;
import com.note.demo.model.NoteRequest;
import com.note.demo.repository.NoteDataSource;

@Service
public class NoteService {

    public List<Note> getNotes() {
        return NoteDataSource.getAllNotes();
    }

    public ResponseEntity<?> getNote(long id) {
        if (id < 1)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Note Id. Id Must Be Greater Than 0!");

        try {
            return ResponseEntity.ok(NoteDataSource.getNote(id));
        } catch (Exception e) {

            if (e instanceof NoSuchElementException)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Note Not Found!");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Request!");
        }
    }

    public ResponseEntity<?> createNote(NoteRequest note) {
        if (!isNoteValid(note))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cannot Create Empty Note!");

        try {
            var id = NoteDataSource.getNextId();
            var newNote = new Note(id, note.getTitle(), note.getBody());
            NoteDataSource.saveNote(newNote);
            return ResponseEntity.ok("Note Saved!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed To Save Note!");
        }
    }

    public ResponseEntity<?> updateNote(long id, NoteRequest note) {
        if (!isNoteValid(note))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cannot Update Empty Note!");

        try {
            var oldNote = NoteDataSource.getNote(id);
            oldNote.setTitle(note.getTitle());
            oldNote.setBody(note.getBody());
            var index = NoteDataSource
                    .getAllNotes()
                    .indexOf(oldNote);
            NoteDataSource.updateNote(index, oldNote);
            return ResponseEntity.ok("Note Updated!");
        } catch (Exception e) {

            if (e instanceof NoSuchElementException)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Note Not Found!");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed To Update Note!");
        }
    }

    public ResponseEntity<?> deleteNote(long id) {
        try {
            var note = NoteDataSource.getNote(id);
            NoteDataSource.deleteNote(note);
            return ResponseEntity.ok("Note Deleted!");
        } catch (Exception e) {

            if (e instanceof NoSuchElementException)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Note Not Found!");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed To Delete Note!");
        }
    }

    private boolean isNoteValid(NoteRequest note) {
        return note.getTitle() != null && note.getBody() != null;
    }
}
