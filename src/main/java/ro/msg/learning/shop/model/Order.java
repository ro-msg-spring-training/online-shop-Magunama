package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="ORDERT")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Shipped_From")
    private Location shippedFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Customer")
    private Customer customer;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String addressCountry;

    @Column
    private String addressCity;

    @Column
    private String addressCounty;

    @Column
    private String addressStreetAddress;
}
