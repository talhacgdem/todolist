package com.talhacgdem.todolist.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoResponseDto {
    private Long id;
    private String date;
    private String description;
    private Boolean status;
}
