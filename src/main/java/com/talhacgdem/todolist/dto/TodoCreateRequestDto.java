package com.talhacgdem.todolist.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoCreateRequestDto {

    private LocalDate date;
    private String description;

}
