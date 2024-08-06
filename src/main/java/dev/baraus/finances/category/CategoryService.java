package dev.baraus.finances.category;

import dev.baraus.finances.category.dto.CategoryInput;
import dev.baraus.finances.category.dto.CategoryOutput;
import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Paginated<CategoryOutput> getAll(PageQuery query) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.direction()), query.sort())
        );

        final var res = categoryRepository.findAll(page);

        return new Paginated<>(res).map(CategoryOutput::from);
    }

    public CategoryOutput save(CategoryInput input) {
        final var category = new Category(input.name(), input.description());

        final var res = categoryRepository.save(category);

        return CategoryOutput.from(res);
    }

    public CategoryOutput getById(Long id) {
        final var res = categoryRepository.findById(id).orElseThrow();
        return CategoryOutput.from(res);
    }

    public CategoryOutput update(Long id, CategoryInput input) {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow();
        categoryToUpdate.setName(input.name());
        categoryToUpdate.setDescription(input.description());

        final var res = categoryRepository.save(categoryToUpdate);

        return CategoryOutput.from(res);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
