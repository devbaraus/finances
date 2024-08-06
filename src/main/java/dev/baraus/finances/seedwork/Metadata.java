package dev.baraus.finances.seedwork;

public record Metadata(int page, int size, long total, long totalPages, boolean hasNext, boolean hasPrevious) {
}
