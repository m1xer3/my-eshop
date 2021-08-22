package ru.danilsibgatullin.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "storage_id")
    private String storageId; // storageUUID;

    @ManyToOne
    private Product product;

    public Picture() {
    }

    public Picture(Long id, String name, String contentType, String storageId, Product product) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.storageId = storageId;
        this.product = product;
    }
}
