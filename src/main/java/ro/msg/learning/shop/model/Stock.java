package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
class StockId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_Id")
    private Product product;
//    private int product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location_Id")
    private Location location;
//    private int location;
}


@Entity
@Data @NoArgsConstructor
@Table
public class Stock {

    @EmbeddedId
    private StockId id;

    @Column
    private int quantity;
}

