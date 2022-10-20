package com.talhacgdem.todolist.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;
}
