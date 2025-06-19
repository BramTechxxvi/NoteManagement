package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateNoteResponse {

    @Id
    private String id;
    private String message;
    private boolean success;
}
