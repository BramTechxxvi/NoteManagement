package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateNoteRequest {

    @NotBlank(message= "Enter note title")
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
