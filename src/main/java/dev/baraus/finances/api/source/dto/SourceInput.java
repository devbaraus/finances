package dev.baraus.finances.api.source.dto;

import java.util.List;

public record SourceInput(String name, String description, List<Long> categories) {
}
