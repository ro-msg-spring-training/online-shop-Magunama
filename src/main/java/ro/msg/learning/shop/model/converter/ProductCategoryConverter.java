package ro.msg.learning.shop.model.converter;

import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.dto.ProductCategoryDTO;

public class ProductCategoryConverter implements EntityDTOConverter <ProductCategory, ProductCategoryDTO> {

    @Override
    public ProductCategoryDTO toDTO(ProductCategory entity) {
        if (entity == null) {
            return null;
        }
        return new ProductCategoryDTO(entity.getId(), entity.getName(), entity.getDescription());

    }

    @Override
    public ProductCategory toEntity(ProductCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ProductCategory(dto.getId(), dto.getName(), dto.getDescription());
    }
}
