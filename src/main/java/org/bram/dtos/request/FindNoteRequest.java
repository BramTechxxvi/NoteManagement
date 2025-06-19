package org.bram.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FindNoteRequest {

    @Id
    private String id;
}
