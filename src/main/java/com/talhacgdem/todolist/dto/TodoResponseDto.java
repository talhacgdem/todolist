package com.talhacgdem.todolist.dto;

import com.talhacgdem.todolist.util.DateUtil;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String date;
    private String description;
    private Boolean status;

    @SuppressWarnings("unused")
    public void setDate(LocalDate date){
        this.date = DateUtil.getLocatedDateString(date);
    }
}
