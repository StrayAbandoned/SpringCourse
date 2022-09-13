package ru.lapshina.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.exception.EntityNotFoundException;
import ru.lapshina.product.ProductDto;
import ru.lapshina.service.ProductService;


import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class MyRestController {

    private final ProductService service;

    @GetMapping
    public Page<ProductDto> showAll(@RequestParam(name = "minCost", required = false) Long minCost,
                                    @RequestParam(name = "maxCost", required = false) Long maxCost,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(10);
        Page<ProductDto> product = service.findPaginated(currentPage, pageSize, minCost, maxCost);
        return product;
    }

    @GetMapping("/{id}")
    public ProductDto showForm(@PathVariable Long id) {
        ProductDto productDto= service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        service.save(productDto);
        return productDto;
    }
    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        service.save(productDto);
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
    }
}
