package ru.lapshina.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductStorage {
    private Map<Integer, Product> products = new ConcurrentHashMap<>();

    private AtomicInteger id = new AtomicInteger(0);

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(long id) {
        return products.get(id);
    }

    public void insert(Product product) {
        int id = this.id.incrementAndGet();
        product.setId(id);
        products.put(id, product);
    }

    public void update(Product product) {
        products.put(product.getId(), product);
    }

    public void delete(int id) {
        products.remove(id);
    }
}