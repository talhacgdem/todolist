package com.talhacgdem.todolist.service;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.dto.converter.TodoConverter;
import com.talhacgdem.todolist.entity.Todo;
import com.talhacgdem.todolist.exception.TodoNotFoundException;
import com.talhacgdem.todolist.repository.TodoRepository;

import com.talhacgdem.todolist.utils.Util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoConverter todoConverter;

    public TodoService(TodoRepository todoRepository, TodoConverter todoConverter) {
        this.todoRepository = todoRepository;
        this.todoConverter = todoConverter;
    }


    public TodoResponseDto newTodo(TodoCreateRequestDto todo) {
        Todo t = todoRepository.save(todoConverter.convertTodoFromTodoCreateRequestDto(todo));
        return todoConverter.convertToTodoResponseFromTodo(t);
    }

    public List<TodoResponseDto> getDaily() {
        return todoRepository.findByDate(LocalDate.now()).stream()
                .map(todoConverter::convertToTodoResponseFromTodo)
                .collect(Collectors.toList());
    }

    public List<TodoResponseDto> getWeekly() {
        return todoRepository.findByDateIsBetweenOrderByDate(
                    DateUtil.getFirstDayOfWeekFromThisWeek(),
                    DateUtil.getLastDayOfWeekFromThisWeek()
                ).stream()
                .map(todoConverter::convertToTodoResponseFromTodo)
                .collect(Collectors.toList());
    }

    public TodoResponseDto accept(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        t.setStatus(true);
        return todoConverter.convertToTodoResponseFromTodo(todoRepository.save(t));
    }

    public TodoResponseDto reject(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        t.setStatus(false);
        return todoConverter.convertToTodoResponseFromTodo(todoRepository.save(t));
    }

    public void delete(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        todoRepository.delete(t);
    }
}
