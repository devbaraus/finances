package dev.baraus.finances.api.source;

import dev.baraus.finances.api.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "source_category", joinColumns = @JoinColumn(name = "source_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Source(String name, String description, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public Source() {

    }
}
