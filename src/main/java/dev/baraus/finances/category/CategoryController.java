package dev.baraus.finances.category;

import dev.baraus.finances.category.dto.CategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public Category getById(Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category save(@RequestBody CategoryInput input) {
        return categoryService.save(input);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Long id, @RequestBody CategoryInput category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
