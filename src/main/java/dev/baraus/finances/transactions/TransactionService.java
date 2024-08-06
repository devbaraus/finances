package dev.baraus.finances.transactions;

import dev.baraus.finances.category.Category;
import dev.baraus.finances.category.CategoryRepository;
import dev.baraus.finances.seedwork.PageQuery;
import dev.baraus.finances.seedwork.Paginated;
import dev.baraus.finances.transactions.dto.TransactionInput;
import dev.baraus.finances.transactions.dto.TransactionOutput;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    final TransactionRepository transactionRepository;
    final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public Paginated<TransactionOutput> getAll(
            PageQuery query
    ) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.direction()), query.sort())
        );
        final var res = transactionRepository.findAll(page);

        return new Paginated<>(res).map(TransactionOutput::from);
    }

    public TransactionOutput save(TransactionInput input) {
        final var category = categoryRepository.findById(input.categoryId()).orElseThrow();

        final var transaction = new Transaction(input.description(), input.amount(), input.date(), category);

        final var res = transactionRepository.save(transaction);

        return TransactionOutput.from(res);
    }

    public TransactionOutput getById(Long id) {
        final var res = transactionRepository.findById(id).orElseThrow();

        return TransactionOutput.from(res);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public TransactionOutput update(Long id, TransactionInput input) {
        Transaction transactionToUpdate = transactionRepository.findById(id).orElseThrow();
        transactionToUpdate.setDescription(input.description());
        transactionToUpdate.setAmount(input.amount());
        transactionToUpdate.setDate(input.date());

        if (input.categoryId() != null) {
            Category category = categoryRepository.findById(input.categoryId()).orElseThrow();
            transactionToUpdate.setCategory(category);
        }

        final var res = transactionRepository.save(transactionToUpdate);

        return TransactionOutput.from(res);
    }
}
