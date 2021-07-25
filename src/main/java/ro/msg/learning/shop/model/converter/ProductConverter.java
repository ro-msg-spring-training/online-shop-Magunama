package ro.msg.learning.shop.model.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.dto.ProductCategoryDTO;
import ro.msg.learning.shop.model.dto.ProductDTO;

@AllArgsConstructor
@Component
public class ProductConverter implements EntityDTOConverter <Product, ProductDTO> {

    private final ProductCategoryConverter productCategoryConverter;

    @Override
    public ProductDTO toDTO(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductCategoryDTO pc = productCategoryConverter.toDTO(entity.getCategory());
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .weight(entity.getWeight())
                .category(pc)
                .imageUrl(entity.getImageUrl()).build();
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductCategory pc = productCategoryConverter.toEntity(dto.getCategory());
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .weight(dto.getWeight())
                .category(pc)
                .imageUrl(dto.getImageUrl()).build();
    }
}
