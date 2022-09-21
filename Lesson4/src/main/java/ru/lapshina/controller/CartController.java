package ru.lapshina.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lapshina.service.ProductService;


@Controller
@RequestMapping("/products/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final ProductService service;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id){
        log.info("We are here");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        service.addToCart(currentUserName, id);
        return "redirect:/products";
    }
    @DeleteMapping("/delete/{id}")
    public String removeFromCart(@PathVariable Long id){
        log.info("We are deleting");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        service.removeFromCart(currentUserName, id);
        return "redirect:/products/cart";
    }
    @GetMapping
    public String showCart(Model model){
        log.info("We are now here");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("cartList",service.showCart(currentUserName) );
        return "cart";
    }
}
