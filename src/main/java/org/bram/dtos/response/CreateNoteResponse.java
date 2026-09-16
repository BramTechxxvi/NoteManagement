package org.bram.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class CreateNoteResponse {

    @Id
    private String id;
    private String message;
    private boolean success;
}
