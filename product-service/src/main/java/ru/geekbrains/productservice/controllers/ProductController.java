package ru.geekbrains.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dtos.ProductDto;
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
    public List<ProductDto> getProductsDto() {
        return productService.findAllProductsDto();
    }

    @GetMapping(value = "/{id}")
    public ProductDto getProductDtoById(@PathVariable(value = "id") Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new RuntimeException
                ("No product found with id = " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findProductById(productDto.getId()).orElseThrow(() -> new RuntimeException
                ("No product found with id = " + productDto.getId()));
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.saveOrUpdate(product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
    }
}
