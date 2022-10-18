package com.talhacgdem.todolist.dto.converter;

import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.entity.Todo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TodoResponseDtoConverter {
    public TodoResponseDto convert(Todo from) {
        return new TodoResponseDto(
                from.getId(),
                getDateString(from.getDate()),
                from.getDescription(),
                from.getStatus()
        );
    }

    public String getDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy, EEEE");
        return date.format(formatter);
    }
}
