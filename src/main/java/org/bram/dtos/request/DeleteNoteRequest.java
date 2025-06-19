package org.bram.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteNoteRequest {
    @Id
    private String id;
    private
}
