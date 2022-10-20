package com.talhacgdem.todolist.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String date;
    private String description;
    private Boolean status;
}
