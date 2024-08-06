package dev.baraus.finances.api.source;

import dev.baraus.finances.api.category.Category;
import dev.baraus.finances.api.category.CategoryRepository;
import dev.baraus.finances.api.source.dto.SourceInput;
import dev.baraus.finances.api.source.dto.SourceOutput;
import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SourceService {
    final SourceRepository groupRepository;
    final CategoryRepository categoryRepository;

    public SourceService(SourceRepository groupRepository, CategoryRepository categoryRepository) {
        this.groupRepository = groupRepository;
        this.categoryRepository = categoryRepository;
    }

    public Paginated<SourceOutput> getAll(
            PageQuery query
    ) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.direction()), query.sort())
        );
        final var res = groupRepository.findAll(page);

        return new Paginated<>(res).map(SourceOutput::from);
    }

    public SourceOutput save(SourceInput input) {
        final var categories = new ArrayList<Category>();

        for (Long categoryId : input.categories()) {
            final var category = categoryRepository.findById(categoryId).orElseThrow();
            categories.add(category);
        }

        final var transaction = new Source(input.name(), input.description(), categories);

        final var res = groupRepository.save(transaction);

        return SourceOutput.from(res);
    }

    public SourceOutput getById(Long id) {
        final var res = groupRepository.findById(id).orElseThrow();

        return SourceOutput.from(res);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    public SourceOutput update(Long id, SourceInput input) {
        Source toUpdate = groupRepository.findById(id).orElseThrow();
        toUpdate.setName(input.name());
        toUpdate.setDescription(input.description());

        final var categories = new ArrayList<Category>();

        for (Long categoryId : input.categories()) {
            final var category = categoryRepository.findById(categoryId).orElseThrow();
            categories.add(category);
        }

        toUpdate.setCategories(categories);

        final var res = groupRepository.save(toUpdate);

        return SourceOutput.from(res);
    }
}
