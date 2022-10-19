package com.talhacgdem.todolist.controller;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.dto.TodoResponseDto;
import com.talhacgdem.todolist.entity.Todo;
import com.talhacgdem.todolist.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo")
@Api(value = "User Api documentation")
public class TodoController {

    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("getDaily")
    @ApiOperation(value = "List daily to-do list")
    public ResponseEntity<List<TodoResponseDto>> getDaily() {
        return ResponseEntity.ok(todoService.getDaily());
    }

    @GetMapping("getWeekly")
    @ApiOperation(value = "List weekly to-do list")
    public ResponseEntity<List<TodoResponseDto>> getWeekly() {
        return ResponseEntity.ok(todoService.getWeekly());
    }

    @PutMapping("new")
    @ApiOperation(value = "Adding a new event to the to-do list")
    public ResponseEntity<TodoResponseDto> newTodo(@RequestBody TodoCreateRequestDto todo) {
        return new ResponseEntity<>(todoService.newTodo(todo), HttpStatus.CREATED);// .created(todoService.newTodo(todo));
    }

    @PatchMapping("accept/{id}")
    @ApiOperation(value = "Mark an event as completed from the to-do list")
    public ResponseEntity<Todo> acceptTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>(new Todo(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(todoService.accept(id), HttpStatus.CREATED);
    }

    @PatchMapping( "reject/{id}")
    @ApiOperation(value = "Mark an event as uncompleted from the to-do list")
    public ResponseEntity<Todo> rejectTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>(new Todo(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(todoService.reject(id), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "Deleting an event from the to-do list")
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>("Todo id must not be null", HttpStatus.BAD_REQUEST);
        todoService.delete(id);
        return ResponseEntity.ok("Todo deleted from id : " + id);
    }
}
