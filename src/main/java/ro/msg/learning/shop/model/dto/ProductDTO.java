package ro.msg.learning.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor @Builder
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private ProductCategoryDTO category;
    private String imageUrl;
}
