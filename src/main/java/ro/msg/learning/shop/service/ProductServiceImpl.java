package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.converter.ProductConverter;
import ro.msg.learning.shop.model.dto.ProductDTO;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;


class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
}

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductConverter productConverter = new ProductConverter();

    ProductServiceImpl (ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product p = this.repository.save(productConverter.toEntity(product));
        return productConverter.toDTO(p);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO newProductDTO) {
        Product oldProduct = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product newProduct = productConverter.toEntity(newProductDTO);
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
        this.repository.save(oldProduct);
        return productConverter.toDTO(oldProduct);
    }

    @Override
    public ProductDTO deleteProduct(int id) {
        Product product = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ProductDTO dto = productConverter.toDTO(product);
        this.repository.deleteById(id);
        return dto;
    }

    @Override
    public ProductDTO getProduct(int id) {
        Product product = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productConverter.toDTO(product);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = (List<Product>) repository.findAll();
        return products.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }
}
