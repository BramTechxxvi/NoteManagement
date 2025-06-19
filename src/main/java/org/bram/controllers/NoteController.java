package org.bram.controllers;

import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.NoteNotFoundException;
import org.bram.services.NoteServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteServices noteServices;

    public NoteController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @PostMapping
    public ResponseEntity<CreateNoteResponse> createNote(@RequestBody CreateNoteRequest request) {
        try {
            CreateNoteResponse response = new CreateNoteResponse();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            CreateNoteResponse errorResponse = new CreateNoteResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Note could not be created");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @
}
