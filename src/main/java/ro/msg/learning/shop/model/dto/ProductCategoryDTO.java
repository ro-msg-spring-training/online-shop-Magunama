package ro.msg.learning.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProductCategoryDTO {
    private int id;
    private String name;
    private String description;
}
