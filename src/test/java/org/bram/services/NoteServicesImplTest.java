package org.bram.services;

import org.bram.data.repositories.NoteRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("tests")
class NoteServicesImplTest {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteServices noteServices;
    private CreateNoteResponse createNoteResponse;
    private FindNoteResponse findNoteResponse;
    private UpdateNoteResponse updateNoteResponse;
    private DeleteNoteResponse deleteNoteResponse;
    private CreateNoteRequest createNoteRequest;
    private FindNoteRequest findNoteRequest;
    private UpdateNoteRequest updateNoteRequest;
    private DeleteNoteRequest deleteNoteRequest;

    @BeforeEach
    void setUp() {
        noteRepository.deleteAll();
        createNoteRequest = new CreateNoteRequest();
        findNoteRequest = new FindNoteRequest();
        updateNoteRequest = new UpdateNoteRequest();
        deleteNoteRequest = new DeleteNoteRequest();
        createNoteResponse = new CreateNoteResponse();
        findNoteResponse = new FindNoteResponse();
        updateNoteResponse = new UpdateNoteResponse();
        deleteNoteResponse = new DeleteNoteResponse();
    }

    @Test
    public void createNoteTest() {
        createNoteRequest.setTitle("English");
        createNoteRequest.setContent("Figures of speech are: \nNouns \nPronouns \nAdverbs \nVerbs \nConjunction \nAdjective");
        createNoteResponse = noteServices.createNote(createNoteRequest);
        assertNotNull(createNoteResponse.getId());
        assertEquals(1, noteRepository.count());
        assertEquals("Successfully created", createNoteResponse.getMessage());
    }

    @Test
    public void findNote__getNoteByIdTest() {
        createNoteTest();
        findNoteRequest.setId(createNoteResponse.getId());
        findNoteResponse = noteServices.getNoteById(findNoteRequest);
        var savedNote = noteRepository.findById(findNoteRequest.getId()).orElseThrow();
        assertEquals("English", savedNote.getTitle());
    }

    @Test
    public void getAllNotesTest() {
        createNoteTest();
        CreateNoteRequest newRequest = new CreateNoteRequest();
        newRequest.setTitle("Biology");
        newRequest.setContent("Topics: \nOrganelles \nCell structure and function \n Cell membrane \nCell cycle and division");
        CreateNoteResponse newResponse = noteServices.createNote(newRequest);
        assertNotNull(newResponse.getId());

        var allNotes = noteServices.getAllNotes();
        assertTrue(allNotes.size() >= 2);
        boolean containsEnglish = allNotes.stream()
                        .anyMatch(note-> "English".equals(note.getTitle()));
        boolean containsBiology = allNotes.stream()
                        .anyMatch(note-> "Biology".equals(note.getTitle()));
        assertTrue(containsBiology);
        assertTrue(containsEnglish);
    }

    @Test
    public void updateANote__updateNoteTest() {
        createNoteTest();
        updateNoteRequest.setNoteId(createNoteResponse.getId());
        updateNoteRequest.setContent("Biochemistry \nBiophysics \nAnatomy \nGenetics \nMolecular Biology \nGenetics");
        updateNoteResponse = noteServices.updateNote(updateNoteRequest);
        assertEquals("Successfully updated", updateNoteResponse.getMessage());
    }

    @Test
    public void deleteNote__deleteNoteTest() {
        createNoteTest();
        deleteNoteRequest.setId(createNoteResponse.getId());
        deleteNoteResponse = noteServices.deleteNote(deleteNoteRequest);
        assertEquals("Successfully deleted", deleteNoteResponse.getMessage());
    }
}