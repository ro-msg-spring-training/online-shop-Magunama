package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
class StockId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Location")
    private Location location;
}


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class Stock {

    @EmbeddedId
    private StockId id;

    @Column
    private int quantity;
}

