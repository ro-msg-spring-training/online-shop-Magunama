package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;


class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
}

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    ProductServiceImpl (ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product newProduct) {
        Product oldProduct = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (newProduct.getName() != null) {
            oldProduct.setName(newProduct.getName());
        }
        if (newProduct.getDescription() != null) {
            oldProduct.setDescription(newProduct.getDescription());
        }
        if (newProduct.getPrice() != null) {
            oldProduct.setPrice(newProduct.getPrice());
        }
        if (newProduct.getWeight() != null) {
            oldProduct.setWeight(newProduct.getWeight());
        }
        if (newProduct.getCategory() != null) {
            oldProduct.setCategory(newProduct.getCategory());
        }
        if (newProduct.getImageUrl() != null) {
            oldProduct.setImageUrl(newProduct.getImageUrl());
        }

        // save old (modified) product
        return this.repository.save(oldProduct);
    }

    @Override
    public void deleteProduct(int id) {
        this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        this.repository.deleteById(id);
    }

    @Override
    public Product getProduct(int id) {
        return this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>) repository.findAll();
    }
}
