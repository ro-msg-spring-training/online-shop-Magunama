package ro.msg.learning.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Stock;

@Data @AllArgsConstructor @NoArgsConstructor
public class StockDTO {

    @JsonIgnore
    Stock.StockId id;

    int product;
    Integer location;
    int quantity;
}
