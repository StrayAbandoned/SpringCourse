package ru.lapshina;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.exception.EntityNotFoundException;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class AppController {

    private final ProductRepository productRepository;

    @GetMapping
    public String showPage(@RequestParam(required = false) Long minCost, @RequestParam(required = false) Long maxCost, Model model) {
        if (minCost == null && maxCost == null) {
            model.addAttribute("productRepository", productRepository.findAll());
        }
        if (minCost != null && maxCost == null) {
            model.addAttribute("productRepository", productRepository.findAllByCostIsGreaterThanEqual(minCost));
        }
        if (minCost == null && maxCost != null) {
            model.addAttribute("productRepository", productRepository.findAllByCostIsLessThanEqual(maxCost));
        }
        if (minCost != null && maxCost != null) {
            model.addAttribute("productRepository", productRepository.betweenCostFilter(minCost,maxCost));
        }
        return "products";
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @PostMapping()
    public String changeProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }


}