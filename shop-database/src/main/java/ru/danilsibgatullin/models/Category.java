package ru.danilsibgatullin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category_name",length = 512,nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {

    }

    public Category(Long id, String s) {
        this.id=id;
        this.categoryName=s;
    }
}
