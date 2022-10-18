package com.talhacgdem.todolist.service;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.dto.converter.TodoCreateRequestDtoConverter;
import com.talhacgdem.todolist.dto.converter.TodoResponseDtoConverter;
import com.talhacgdem.todolist.entity.Todo;
import com.talhacgdem.todolist.exception.TodoNotFoundException;
import com.talhacgdem.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoCreateRequestDtoConverter todoCreateRequestDtoConverter;

    private final TodoResponseDtoConverter todoResponseDtoConverter;

    public TodoService(TodoRepository todoRepository, TodoCreateRequestDtoConverter todoCreateRequestDtoConverter, TodoResponseDtoConverter todoResponseDtoConverter) {
        this.todoRepository = todoRepository;
        this.todoCreateRequestDtoConverter = todoCreateRequestDtoConverter;
        this.todoResponseDtoConverter = todoResponseDtoConverter;
    }


    public TodoResponseDto newTodo(TodoCreateRequestDto todo) {
        Todo t = todoRepository.save(todoCreateRequestDtoConverter.convert(todo));
        return todoResponseDtoConverter.convert(t);
    }

    public List<TodoResponseDto> getDaily() {
        return todoRepository.findByDate(LocalDate.now()).stream()
                .map(todoResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public List<TodoResponseDto> getWeekly() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.with(ChronoField.DAY_OF_WEEK, 1);
        LocalDate lastDay = firstDay.plusDays(6);
        return todoRepository.findByDateIsBetweenOrderByDate(firstDay, lastDay).stream()
                .map(todoResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public Todo accept(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );

        t.setStatus(true);
        return todoRepository.save(t);
    }

    public Todo reject(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );

        t.setStatus(false);
        return todoRepository.save(t);
    }

    public void delete(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        todoRepository.delete(t);
    }
}
