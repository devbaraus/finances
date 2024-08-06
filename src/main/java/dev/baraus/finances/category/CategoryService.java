package dev.baraus.finances.category;

import dev.baraus.finances.category.dto.CategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryInput input) {
        return categoryRepository.save(input.toEntity());
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, CategoryInput input) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate == null) {
            return null;
        }
        categoryToUpdate.setName(input.name());
        return categoryRepository.save(categoryToUpdate);
    }
}
