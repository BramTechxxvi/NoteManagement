package org.bram.services;

import org.bram.data.models.Note;
import org.bram.data.repositories.NoteRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServicesImpl implements NoteServices {

    private NoteRepository noteRepository;

    public NoteServicesImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle().trim());
        note.setContent(request.getContent());
        note.setCreatedAt(LocalDateTime.now());
        Note savedNote = noteRepository.save(note);

        CreateNoteResponse response = new CreateNoteResponse();
        response.setId(savedNote.getId());
        response.setMessage("Successfully created");
        response.setSuccess(true);

        return response;
    }

    @Override
    public FindNoteResponse getNoteById(FindNoteRequest request) {
        Note note = noteRepository.findById(request.getId())
                .orElseThrow(()-> new NoteNotFoundException("Note not found"));

        FindNoteResponse response = new FindNoteResponse();
        response.setNote(note);
        response.setSuccess(true);
        return response;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest request) {
        Note note = noteRepository.findById(request.getNoteId())
                .orElseThrow(()-> new NoteNotFoundException("Note not found"));

        boolean validTitle = request.getTitle() != null && !request.getTitle().trim().isEmpty();
        if(validTitle) note.setTitle(request.getTitle().trim());

        note.setContent(request.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        noteRepository.save(note);
        UpdateNoteResponse response = new UpdateNoteResponse();
        response.setSuccess(true);
        response.setMessage("Successfully updated");

        return response;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest request) {

         null;
    }
}
