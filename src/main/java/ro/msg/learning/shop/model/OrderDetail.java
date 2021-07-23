package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class OrderDetail {

    @Data @AllArgsConstructor @NoArgsConstructor
    @Embeddable
    public static class OrderDetailId implements Serializable {

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "Orderc")
        private Order order;

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "Product")
        private Product product;
    }

    @EmbeddedId
    private OrderDetailId id;

    @Column
    private int quantity;
}

