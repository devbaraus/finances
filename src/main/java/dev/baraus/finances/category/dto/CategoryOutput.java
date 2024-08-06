package dev.baraus.finances.category.dto;

import dev.baraus.finances.category.Category;

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


