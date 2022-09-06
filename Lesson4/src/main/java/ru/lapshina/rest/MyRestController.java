package ru.lapshina.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.exception.EntityNotFoundException;
import ru.lapshina.product.ProductDto;
import ru.lapshina.service.ProductService;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class MyRestController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> showAll(@RequestParam(name = "minCost", required = false) Long minCost,
                          @RequestParam(name = "maxCost", required = false) Long maxCost) {
        List <ProductDto> product = service.showAll(minCost, maxCost);
        return product;
    }

    @GetMapping("/{id}")
    public ProductDto showForm(@PathVariable Long id) {
        ProductDto productDto= service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }

    @PostMapping()
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new IllegalArgumentException("Created user shouldn't have id");
        }
        service.save(productDto);
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
    }
}
