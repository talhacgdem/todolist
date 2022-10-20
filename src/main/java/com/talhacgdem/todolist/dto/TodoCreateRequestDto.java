package com.talhacgdem.todolist.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoCreateRequestDto {

    private LocalDate date;
    private String description;

}
