package dev.baraus.finances.category.dto;

import dev.baraus.finances.category.Category;

public record CategoryInput(String name) {
    public Category toEntity() {
        return new Category(name);
    }
}
