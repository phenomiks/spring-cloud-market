package ru.geekbrains.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.productservice.entities.Product;
import ru.geekbrains.productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable(value = "id") Long id) {
        return productService.findProductById(id).orElseThrow(() -> new RuntimeException
                ("No product found with id = " + id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.saveOrUpdate(product);
    }
}
