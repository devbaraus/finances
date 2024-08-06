package dev.baraus.finances.api.source;

import dev.baraus.finances.api.source.dto.SourceInput;
import dev.baraus.finances.api.source.dto.SourceOutput;
import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groups")
@Tag(name = "Groups")
public class SourceController {
    final SourceService groupService;

    public SourceController(SourceService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<Paginated<SourceOutput>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        final var query = new PageQuery(page, size, sort, direction);
        final var res = groupService.getAll(query);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SourceOutput> getById(@PathVariable Long id) {
        final var res = groupService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SourceOutput> save(@RequestBody SourceInput input) {
        final var res = groupService.save(input);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<SourceOutput> update(@PathVariable Long id, @RequestBody SourceInput input) {
        final var res = groupService.update(id, input);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}
