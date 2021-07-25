package ro.msg.learning.shop.service;


import ro.msg.learning.shop.model.Product;

import java.util.List;


public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(int id, Product product);
    void deleteProduct(int id);
    Product getProduct(int id);
    List<Product> getProducts();
}
