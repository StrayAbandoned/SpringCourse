package ru.lapshina.product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@org.springframework.stereotype.Repository (value = "productRepository")
public class ProductRepository implements Repository{
    private Map<Integer, Product> products = new ConcurrentHashMap<>();

    private AtomicInteger id = new AtomicInteger(0);

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(int id) {
        return products.get(id);
    }

    public void insert(Product product) {
        int id = this.id.incrementAndGet();
        product.setId(id);
        products.put(id, product);
    }

    @PostConstruct
    public void init() {
        insert(new Product("Pineapple", 105));
        insert(new Product("Cucumber", 40));
        insert(new Product("Broccoli", 180));
        insert(new Product("Orange", 95));
        insert(new Product("Kiwi", 120));
    }
    public void show(){
        List<Product> products = this.findAll();
        for (Product p: products){
            String format = String.format("%d %s %d", p.getId(), p.getTitle(), p.getCost());
            System.out.println(format);
        }
    }
}
