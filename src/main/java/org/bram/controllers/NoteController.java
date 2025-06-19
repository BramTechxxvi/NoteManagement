package org.bram.controllers;

import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.NoteNotFoundException;
import org.bram.services.NoteServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteServices noteServices;

    public NoteController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateNoteResponse> createNote(@RequestBody CreateNoteRequest request) {
        try {
            CreateNoteResponse response = noteServices.createNote(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            CreateNoteResponse errorResponse = new CreateNoteResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getANote/{id}")
    public ResponseEntity<FindNoteResponse> getNoteById(@PathVariable("id") String id, @RequestBody FindNoteRequest request) {
        try {
            request.setId(id);
            FindNoteResponse response = noteServices.getNoteById(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (NoteNotFoundException e) {
            FindNoteResponse errorResponse = new FindNoteResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getAllNotes")
    public ResponseEntity<List>
}
