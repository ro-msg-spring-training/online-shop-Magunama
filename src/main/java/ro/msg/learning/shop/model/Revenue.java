package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data @NoArgsConstructor
@Table
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location_Id")
    private Location location;
//    @Column
//    private int location;

    @Column
    private LocalDate date;

    @Column
    private BigDecimal sum;

}
