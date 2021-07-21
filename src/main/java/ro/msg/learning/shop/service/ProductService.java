package ro.msg.learning.shop.service;


import ro.msg.learning.shop.model.dto.ProductDTO;

import java.util.List;


public interface ProductService {
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(int id, ProductDTO product);
    ProductDTO deleteProduct(int id);
    ProductDTO getProduct(int id);
    List<ProductDTO> getProducts();
}
