package ru.geekbrains.frontservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dtos.ProductDto;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/products")
public class FrontController {
    private final ProductServiceClient productServiceClient;
    private String pathAnnotation;

    @Autowired
    public FrontController(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    @PostConstruct
    private void init() {
        pathAnnotation = getRequestMappingAnnotationPath();
    }

    @GetMapping
    public String getProductsDto(Model model) {
        List<ProductDto> productDtos = productServiceClient.getProductsDto();
        model.addAttribute("allProducts", productDtos);
        model.addAttribute("path", pathAnnotation);
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getProductDtoById(@PathVariable(value = "id") String id, Model model) {
        ProductDto productDto = productServiceClient.getProductDtoById(Long.parseLong(id));
        model.addAttribute("allProducts", productDto);
        return "index";
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String saveProduct(@ModelAttribute ProductDto productDto, Model model) {
        productServiceClient.saveProduct(productDto);
        model.addAttribute("path", pathAnnotation);
        return "redirect:" + pathAnnotation;
    }

    @PutMapping
    public String updateProduct(@ModelAttribute ProductDto productDto, Model model) {
        productServiceClient.updateProduct(productDto);
        model.addAttribute("path", pathAnnotation);
        return "redirect:" + pathAnnotation;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProductById(@PathVariable(value = "id") String id) {
        productServiceClient.deleteProductById(Long.parseLong(id));
        return "redirect:" + pathAnnotation;
    }

    private String getRequestMappingAnnotationPath() {
        String path = this.getClass().getAnnotation(RequestMapping.class).path()[0];
        if (path == null) {
            throw new RuntimeException("Unknown path in @RequestMapping");
        }
        return path;
    }
}
