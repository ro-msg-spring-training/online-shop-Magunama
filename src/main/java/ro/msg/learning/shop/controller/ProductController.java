package ro.msg.learning.shop.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.converter.ProductConverter;
import ro.msg.learning.shop.model.dto.ProductDTO;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;
    private final ProductConverter productConverter;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<Product> products = this.service.getProducts();
        List<ProductDTO> productsDto = products.stream().map(productConverter::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        Product product = this.service.getProduct(id);
        return ResponseEntity.ok(productConverter.toDTO(product));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = this.service.createProduct(productConverter.toEntity(productDTO));
        return ResponseEntity.ok(productConverter.toDTO(newProduct));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable int id) {
        Product newProduct = this.service.updateProduct(id, productConverter.toEntity(productDTO));
        return ResponseEntity.ok(productConverter.toDTO(newProduct));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity.BodyBuilder deleteProduct(@PathVariable int id) {
        this.service.deleteProduct(id);
        return ResponseEntity.ok();
    }

}