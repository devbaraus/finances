package dev.baraus.finances.seedwork;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;


public record Paginated<T>(Metadata meta, List<T> data) {
    public Paginated(Page<T> page) {
        this(new Metadata(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.hasNext(), page.hasPrevious()), page.getContent());
    }

    public <R> Paginated<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = this.data.stream().map(mapper).toList();

        return new Paginated<>(meta(), aNewList);
    }


}

