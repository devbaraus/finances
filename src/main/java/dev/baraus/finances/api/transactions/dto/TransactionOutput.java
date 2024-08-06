package dev.baraus.finances.api.transactions.dto;

import dev.baraus.finances.api.transactions.Transaction;

import java.util.Date;

public record TransactionOutput(Long id,
                                String description,
                                Double amount,
                                Date date,
                                Date createdAt,
                                Date updatedAt,
                                Long categoryId) {

    public static TransactionOutput from(Transaction transaction) {
        return new TransactionOutput(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt(),
                transaction.getCategory().getId()
        );
    }
}


