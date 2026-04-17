package com.yasminebelmiro.todo_list.entity;

import java.io.Serializable;
import java.util.Date;

import com.yasminebelmiro.todo_list.enums.PriorityEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "todos")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = true, length = 100)
    private String description;
    @Column(nullable = false, length = 1)
    private boolean completed;
    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.ORDINAL)
    private PriorityEnum priority;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_list_id", nullable = false)
    private TaskList taskList;
    private Date createdAt;

    public Task() {
    }

    public Task(String description, Long id, String name, PriorityEnum priority, boolean completed) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.completed = completed;
    }

    public void atualizarStatus() {
        this.completed = !this.completed;
    }

}
