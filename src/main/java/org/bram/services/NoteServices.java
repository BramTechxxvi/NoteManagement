package org.bram.services;

import org.bram.data.models.Note;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;

import java.util.List;

public interface NoteServices {

    CreateNoteResponse createNote(CreateNoteRequest request);
    FindNoteResponse getNoteById(FindNoteRequest request);
    List<Note> getAllNotes();

    UpdateNoteResponse updateNote(UpdateNoteRequest request);

    DeleteNoteResponse deleteNote(DeleteNoteRequest request);
}
