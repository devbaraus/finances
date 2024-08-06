package dev.baraus.finances.api.source.dto;

import dev.baraus.finances.api.category.Category;
import dev.baraus.finances.api.source.Source;

import java.util.List;

public record SourceOutput(Long id, String name, String description, List<Long> categories) {
    public static SourceOutput from(Source group) {
        return new SourceOutput(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getCategories().stream().map(Category::getId
                ).toList()
        );
    }
}
