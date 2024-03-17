package com.note.demo.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import com.note.demo.model.Note;

public class NoteDataSource {
    private static List<Note> noteStore = new ArrayList<>();

    public static List<Note> getAllNotes() {
        return noteStore;
    }

    public static Note getNote(long id) {
        return noteStore
                .stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public static void saveNote(Note note) {
        noteStore.add(note);
    }

    public static void updateNote(int index, Note note) {
        noteStore.set(index, note);
    }

    public static void deleteNote(Note note) {
        noteStore.remove(note);
    }

    public static long getNextId() {
        if (noteStore.isEmpty())
            return 1L;

        var maxId = noteStore
                .stream()
                .max(Comparator.comparing(Note::getId))
                .get()
                .getId();

        return maxId + 1;
    }
}
