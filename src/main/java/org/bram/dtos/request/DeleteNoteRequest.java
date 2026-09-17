package org.bram.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class DeleteNoteRequest {
    @Id
    private String id;
}
