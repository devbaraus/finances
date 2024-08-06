package dev.baraus.finances.seedwork;

public record PageQuery(
        int page,
        int perPage,
        String sort,
        String direction
) {
}
