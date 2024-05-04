package com.holotsvan.springboottodo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "todo_items")
public class ToDoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    private Boolean isCompleted;

    private Instant createdAt;

    private Instant updatedAt;

    @Override
    public String toString() {
        return "ToDoItem{" +
               "id=" + id +
               ", description='" + description + '\'' +
               ", isCompleted=" + isCompleted +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }
}
