package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNoteRequest {

    @NotBlank(message= "Enter note title")
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
