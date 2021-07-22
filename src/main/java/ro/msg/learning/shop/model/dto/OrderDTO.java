package ro.msg.learning.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    private int id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime createdAt;

    private String addressCountry;

    private String addressCity;

    private String addressCounty;

    private String addressStreetAddress;

    private List<StockDTO> products;

    public static Integer getQuantityById(List<StockDTO> orderProducts, Integer productId) {
        for (StockDTO op: orderProducts) {
            if (op.getProduct() == productId) {
                return op.getQuantity();
            }
        }
        return null;
    }
}

