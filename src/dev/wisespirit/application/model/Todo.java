package dev.wisespirit.application.model;

import dev.wisespirit.application.annotations.Entity;
import dev.wisespirit.application.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Todo {
    private Integer id;
    private Integer userId;
    private String task;
    private String description;
    private LocalDateTime createdAt;
    private Status status = Status.NOT_STARTED;

    public Todo(){

    }

    public Todo(Integer id, Integer userId, String task, String description, LocalDateTime createdAt, Status status) {
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userId=" + userId +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Todo todo)) return false;
        return Objects.equals(id, todo.id) && Objects.equals(userId, todo.userId) && Objects.equals(task, todo.task) && Objects.equals(description, todo.description) && Objects.equals(createdAt, todo.createdAt) && status == todo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, task, description, createdAt, status);
    }
}
