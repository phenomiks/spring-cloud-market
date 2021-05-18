package ru.geekbrains.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.productservice.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductControll {
    private final RestTemplate restTemplate;

    @Autowired
    public ProductControll(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<?> products = restTemplate.getForObject("http://localhost:8192/app/api/v1/products/", List.class);
        model.addAttribute("allProducts", products);
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getProductById(@PathVariable(value = "id") String id, Model model) {
        Product product = restTemplate.getForObject("http://localhost:8192/app/api/v1/products/" + id, Product.class);
        model.addAttribute("allProducts", product);
        return "index";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProductById(@PathVariable(value = "id") String id) {
        restTemplate.delete("http://localhost:8192/app/api/v1/products/" + id);
        return "redirect:/";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveProduct(@RequestParam String title,
                              @RequestParam BigDecimal price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        restTemplate.postForObject("http://localhost:8192/app/api/v1/products/", product, Product.class);
        return "redirect:/";
    }

    @PutMapping
    public String updateProduct(@ModelAttribute Product product) {
        restTemplate.put("http://localhost:8192/app/api/v1/products/", product);
        return "redirect:/";
    }
}
