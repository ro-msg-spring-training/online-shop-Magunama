package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data @NoArgsConstructor
@Table(name="`Order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location_Id")
    private Location shippedFrom;
//    @Column
//    private int shippedFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_Id")
    private Customer customer;
//    @Column
//    private int customer;

    @Column
    private LocalDateTime createdAt;

    @Column(name = "Address.Country")
    private String address_country;

    @Column(name = "Address.City")
    private String address_city;

    @Column(name = "Address.County")
    private String address_county;

    @Column(name = "Address.StreetAddress")
    private String address_streetAddress;
}
