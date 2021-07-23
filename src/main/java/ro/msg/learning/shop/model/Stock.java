package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class Stock {

    @Data
    @Embeddable @AllArgsConstructor @NoArgsConstructor
    public static class StockId implements Serializable {

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "Product")
        private Product product;

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "Location")
        private Location location;
    }

    @EmbeddedId
    private StockId id;

    @Column
    private int quantity;
}

