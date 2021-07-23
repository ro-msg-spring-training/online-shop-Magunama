package ro.msg.learning.shop.model.converter;

import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.dto.ProductCategoryDTO;
import ro.msg.learning.shop.model.dto.ProductDTO;

public class ProductConverter implements EntityDTOConverter <Product, ProductDTO> {

    private final ProductCategoryConverter productCategoryConverter = new ProductCategoryConverter();

    @Override
    public ProductDTO toDTO(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductCategoryDTO pc = productCategoryConverter.toDTO(entity.getCategory());
        return new ProductDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(),
                entity.getWeight(), pc, entity.getImageUrl());
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductCategory pc = productCategoryConverter.toEntity(dto.getCategory());
        return new Product(dto.getId(), dto.getName(), dto.getDescription(), dto.getPrice(), dto.getWeight(),
                pc, null, dto.getImageUrl());
    }
}
