package com.talhacgdem.todolist.dto.converter;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.entity.Todo;

import org.springframework.stereotype.Component;

@Component
public class TodoCreateRequestDtoConverter {
    public Todo convert(TodoCreateRequestDto from){
        return new Todo(
          null,
          from.getDate(),
          from.getDescription(),
          false
        );
    }
}
