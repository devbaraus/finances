package dev.baraus.finances.transactions;

import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import dev.baraus.finances.transactions.dto.TransactionInput;
import dev.baraus.finances.transactions.dto.TransactionOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
@Tag(name = "Transactions")
public class TransactionController {
    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<Paginated<TransactionOutput>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "date") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        final var query = new PageQuery(page, size, sort, direction);
        final var res = transactionService.getAll(query);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionOutput> getById(@PathVariable Long id) {
        final var res = transactionService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionOutput> save(TransactionInput transaction) {
        final var res = transactionService.save(transaction);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TransactionOutput> update(@PathVariable Long id, @RequestBody TransactionInput transaction) {
        final var res = transactionService.update(id, transaction);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
