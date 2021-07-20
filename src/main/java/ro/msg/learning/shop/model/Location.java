package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
@Table
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column(name = "Address.Country")
    private String address_country;

    @Column(name = "Address.City")
    private String address_city;

    @Column(name = "Address.County")
    private String address_county;

    @Column(name = "Address.StreetAddress")
    private String address_streetAddress;
}
