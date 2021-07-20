package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
@Table
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    public Supplier (String name) {
        this.name = name;
    }
}
