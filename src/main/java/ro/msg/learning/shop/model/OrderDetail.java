package ro.msg.learning.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
class OrderDetailId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`Order`")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product")
    private Product product;
}


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

    @Column
    private int quantity;
}

