package com.company.inventory.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private double price;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
    private Category category;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", columnDefinition = "longblob")
    private byte[] picture;


}
