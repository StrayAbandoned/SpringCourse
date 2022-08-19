package ru.lapshina;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductRepository;

import javax.validation.Valid;

@Slf4j
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
    public String changeProduct(@Valid Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product_form";
        }


        productRepository.save(product);
        return "redirect:/products";

    }

    @GetMapping("/new")
    public String showForm(Model model) {
        Product p = new Product(" ", 0);
        productRepository.insert(p);
        return "redirect:/products/" + p.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productRepository.delete(id);
        return "redirect:/products";
    }


}