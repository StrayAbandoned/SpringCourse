package ru.lapshina.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component(value = "cart")
@Scope("prototype")
public class Cart {
    @Autowired
    private ProductRepository productRepository;
    private Map<Product, Integer> cartProducts = new ConcurrentHashMap<>();


    public List<Product> findAll() {
        return new ArrayList<>(cartProducts.keySet());
    }

    public Product findById(int id) {
        for (Product p: cartProducts.keySet())
            if (p.getId()==id) {
                return p;
            }
        return null;
    }
    public void add(Product product, int count) {
        cartProducts.merge(product,count, Integer::sum);
    }

    public void delete(Product product, int count) {
        if (cartProducts.get(product)==1){
            cartProducts.remove(product);
        }
        else {
            cartProducts.merge(product,-count, Integer::sum);
        }

    }

    public void show(){
        List<Product> products = this.findAll();
        for (Product p: products){
            String format = String.format("%d %s %d - %d шт.", p.getId(), p.getTitle(), p.getCost(), cartProducts.get(p));
            System.out.println(format);
        }
    }
}
