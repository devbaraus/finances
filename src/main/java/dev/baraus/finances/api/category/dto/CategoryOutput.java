package dev.baraus.finances.api.category.dto;

import dev.baraus.finances.api.category.Category;

import java.util.Date;

public record CategoryOutput(Long id,
                             String name,
                             String description,
                             Date createdAt,
                             Date updatedAt) {

    public static CategoryOutput from(Category category) {
        return new CategoryOutput(category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt());
    }
}


