package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data @NoArgsConstructor
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductCategory_Id")
    private ProductCategory category;
//    @Column
//    private int category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Supplier_Id")
    private Supplier supplier;
//    @Column
//    private int supplier;

    @Column
    private String imageUrl;

//    public Product(String name, String description, BigDecimal price, Double weight, ProductCategory category,
//                   Supplier supplier, String imageUrl) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.weight = weight;
//        this.category = category;
//        this.supplier = supplier;
//        this.imageUrl = imageUrl;
//    }
}
