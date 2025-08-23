package dev.wisespirit.application.model.dto;

import java.time.LocalDateTime;

public record TodoDto(Integer userId, String task, String description, LocalDateTime createdAt,String status) {
}
