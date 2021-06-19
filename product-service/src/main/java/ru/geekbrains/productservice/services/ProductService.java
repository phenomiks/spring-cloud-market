package ru.geekbrains.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.dtos.ProductDto;
import ru.geekbrains.productservice.entities.Product;
import ru.geekbrains.productservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(value -> new ProductDto(value.getId(), value.getTitle(), value.getPrice()));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductDto> findAllProductsDto() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos = new ArrayList<>();
        products.forEach(p -> dtos.add(new ProductDto(p.getId(), p.getTitle(), p.getPrice())));
        return dtos;
    }

    public ProductDto saveOrUpdate(Product product) {
        Product p = productRepository.save(product);
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
