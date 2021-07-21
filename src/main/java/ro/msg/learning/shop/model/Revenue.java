package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location")
    private Location location;

    @Column
    private LocalDate date;

    @Column
    private BigDecimal sum;

}
