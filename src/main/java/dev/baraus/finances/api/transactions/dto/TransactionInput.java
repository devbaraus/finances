package dev.baraus.finances.api.transactions.dto;

import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record TransactionInput(
        @NonNull String description,
        @NonNull Double amount,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @NonNull Date date,
        Long categoryId
) {
}
