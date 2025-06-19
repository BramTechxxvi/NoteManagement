package org.bram.dtos.response;

import lombok.Data;
import org.bram.data.models.Note;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class FindNoteResponse {

    @DBRef
    private Note note;
    private boolean success;
    private Str
}
