package ro.msg.learning.shop.model.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.dto.ProductCategoryDTO;

@Component
public class ProductCategoryConverter implements EntityDTOConverter <ProductCategory, ProductCategoryDTO> {

    @Override
    public ProductCategoryDTO toDTO(ProductCategory entity) {
        if (entity == null) {
            return null;
        }

        return ProductCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription()).build();
        }

    @Override
    public ProductCategory toEntity(ProductCategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return ProductCategory.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription()).build();
    }
}
