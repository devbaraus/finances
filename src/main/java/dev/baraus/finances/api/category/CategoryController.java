package dev.baraus.finances.api.category;

import dev.baraus.finances.api.category.dto.CategoryInput;
import dev.baraus.finances.api.category.dto.CategoryOutput;
import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@Tag(name = "Categories")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Paginated<CategoryOutput>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        final var query = new PageQuery(page, size, sort, direction);
        final var res = categoryService.getAll(query);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryOutput> getById(Long id) {
        final var res = categoryService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryOutput> save(@RequestBody CategoryInput input) {
        final var res = categoryService.save(input);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryOutput> update(@PathVariable Long id, @RequestBody CategoryInput category) {
        final var res = categoryService.update(id, category);
        return new ResponseEntity<>(res,  HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
