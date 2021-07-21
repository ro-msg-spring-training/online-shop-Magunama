package ro.msg.learning.shop.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.dto.ProductDTO;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(this.service.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        return ResponseEntity.ok(this.service.getProduct(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO newProduct) {
        return ResponseEntity.ok(this.service.createProduct(newProduct));

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO newProduct, @PathVariable int id) {
        return ResponseEntity.ok(this.service.updateProduct(id, newProduct));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(this.service.deleteProduct(id));
    }

}

