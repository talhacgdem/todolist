package com.talhacgdem.todolist.repository;

import com.talhacgdem.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByDate(LocalDate date);
    List<Todo> findByDateIsBetweenOrderByDate(LocalDate date1, LocalDate date2);

}
