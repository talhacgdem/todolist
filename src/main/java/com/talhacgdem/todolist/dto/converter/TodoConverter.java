package com.talhacgdem.todolist.dto.converter;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.entity.Todo;
import com.talhacgdem.todolist.utils.Util.DateUtil;
import org.springframework.stereotype.Component;



@Component
public class TodoConverter {

    public Todo convertTodoFromTodoCreateRequestDto(TodoCreateRequestDto from){
        return Todo.builder()
                .date(from.getDate())
                .description(from.getDescription())
                .status(false)
                .build();
    }

    public TodoResponseDto convertToTodoResponseFromTodo(Todo from) {
        return new TodoResponseDto(
                from.getId(),
                DateUtil.getLocatedDateString(from.getDate()),
                from.getDescription(),
                from.getStatus()
        );
    }



}
