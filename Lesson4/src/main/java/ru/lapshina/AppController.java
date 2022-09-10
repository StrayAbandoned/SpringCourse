package ru.lapshina;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.exception.EntityNotFoundException;
import ru.lapshina.product.ProductDto;
import ru.lapshina.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class AppController {

    private final ProductService service;

    @GetMapping
    public String showAll(@RequestParam(name = "minCost", required = false) Long minCost,
                          @RequestParam(name = "maxCost", required = false) Long maxCost,
                          @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          Model model) {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(10);
        Page<ProductDto> productPage = service.findPaginated(currentPage, pageSize, minCost, maxCost);
        model.addAttribute("products", productPage);
        return "products";
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @PostMapping()
    public String changeProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        service.save(productDto);
        return "redirect:/products";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }

}