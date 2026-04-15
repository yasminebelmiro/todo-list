package com.yasminebelmiro.todo_list.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String nome;
    @Column(nullable = true, length = 100)
    private String descricao;
    @Column(nullable = false, length = 1)
    private boolean realizada;
    @Column(nullable = false, length = 1)
    private Integer prioridade;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
    }

    public Task(String descricao, Long id, String nome, Integer prioridade, boolean realizada) {
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.realizada = realizada;
    }

    public void atualizarStatus() {
        this.realizada = !this.realizada;
    }

}
