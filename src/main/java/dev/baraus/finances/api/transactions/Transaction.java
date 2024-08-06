package dev.baraus.finances.api.transactions;

import dev.baraus.finances.api.category.Category;
import dev.baraus.finances.api.source.Source;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private TransactionType type;

//    private Boolean isRecurrent;
//
//    private Integer recurrentTimes;
//
//    private Integer recurrentInterval;

    private Date date;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Source source;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Transaction() {
    }

    public Transaction(String description, Double amount, Date date, Category category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
}
