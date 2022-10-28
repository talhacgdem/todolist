package com.talhacgdem.todolist.service;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.entity.Todo;
import com.talhacgdem.todolist.exception.TodoNotFoundException;
import com.talhacgdem.todolist.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.talhacgdem.todolist.util.DateUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    public TodoResponseDto newTodo(TodoCreateRequestDto todo) {
        Todo t = todoRepository.save(
                modelMapper.map(todo, Todo.class)
        );
        return modelMapper.map(t, TodoResponseDto.class);
    }

    public List<TodoResponseDto> getDaily() {
        return todoRepository.findByDate(LocalDate.now()).stream()
                .map(todo -> modelMapper.map(todo, TodoResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<TodoResponseDto> getWeekly() {
        return todoRepository.findByDateIsBetweenOrderByDate(
                        DateUtil.getFirstDayOfWeekFromThisWeek(),
                        DateUtil.getLastDayOfWeekFromThisWeek()
                ).stream()
                .map(todo -> modelMapper.map(todo, TodoResponseDto.class))
                .collect(Collectors.toList());
    }

    public TodoResponseDto accept(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        t.setStatus(true);
        return modelMapper.map(todoRepository.save(t), TodoResponseDto.class);
    }

    public TodoResponseDto reject(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        t.setStatus(false);
        return modelMapper.map(todoRepository.save(t), TodoResponseDto.class);
    }

    public void delete(Long id) {
        Todo t = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(id)
        );
        todoRepository.delete(t);
    }
}
