package com.talhacgdem.todolist.controller;

import com.talhacgdem.todolist.dto.TodoCreateRequestDto;
import com.talhacgdem.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("getDaily")
    public ResponseEntity<?> getDaily() {
        return ResponseEntity.ok(todoService.getDaily());
    }

    @GetMapping("getWeekly")
    public ResponseEntity<?> getWeekly() {
        return ResponseEntity.ok(todoService.getWeekly());
    }

    @PostMapping("new")
    public ResponseEntity<?> newTodo(@RequestBody TodoCreateRequestDto todo) {
        return ResponseEntity.ok(todoService.newTodo(todo));
    }

    @GetMapping(value = {"accept/", "accept", "accept/{id}", "accept/{id}/"})
    public ResponseEntity<?> acceptTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>("Todo id must not be null", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(todoService.accept(id));
    }

    @GetMapping(value = {"reject/", "reject", "reject/{id}", "reject/{id}/"})
    public ResponseEntity<?> rejectTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>("Todo id must not be null", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(todoService.reject(id));
    }

    @GetMapping(value = {"delete/", "delete", "delete/{id}", "delete/{id}/"})
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity<>("Todo id must not be null", HttpStatus.BAD_REQUEST);
        todoService.delete(id);
        return ResponseEntity.ok("Todo deleted from id : " + id);
    }
}
