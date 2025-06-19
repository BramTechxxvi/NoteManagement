package org.bram.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UpdateNoteRequest {

    @Id
    private String noteId;
    private String title;
    private String content;
    private LocalDateTime updateDate;
}
