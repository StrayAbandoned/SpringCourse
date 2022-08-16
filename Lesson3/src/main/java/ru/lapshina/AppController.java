package ru.lapshina;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductRepository;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class AppController {

    private final ProductRepository productRepository;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("productRepository", productRepository.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping()
    public String changeProduct(Product product) {
        productRepository.update(product);
        return "redirect:/products";

    }

    @GetMapping("/new")
    public String showForm(Model model) {
        Product product = new Product();
        model.addAttribute("newProduct", product);
        productRepository.insert(product);
        model.addAttribute("id", product.getId());
        return "redirect:/products/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productRepository.delete(id);
        return "redirect:/products";
    }


}
