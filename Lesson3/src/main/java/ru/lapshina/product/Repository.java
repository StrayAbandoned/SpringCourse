package ru.lapshina.product;

import java.util.List;

public interface Repository {
    List<Product> findAll();
    Product findById(int id);

}