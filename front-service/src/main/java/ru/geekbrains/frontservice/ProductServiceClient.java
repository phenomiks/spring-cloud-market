package ru.geekbrains.frontservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dtos.ProductDto;

import java.util.List;

@FeignClient(value = "PRODUCT-SERVICE", path = "/api/v1/products")
public interface ProductServiceClient {

    @GetMapping
    List<ProductDto> getProductsDto();

    @GetMapping(value = "/{id}")
    ProductDto getProductDtoById(@PathVariable(value = "id") Long id);

    @PostMapping
    ProductDto saveProduct(@RequestBody ProductDto productDto);

    @PutMapping
    void updateProduct(@RequestBody ProductDto productDto);

    @DeleteMapping(value = "/{id}")
    void deleteProductById(@PathVariable(value = "id") Long id);
}
