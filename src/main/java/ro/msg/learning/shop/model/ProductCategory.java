package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
@Table
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    public ProductCategory (String name, String description) {
        this.name = name;
        this.description = description;
    }
}
