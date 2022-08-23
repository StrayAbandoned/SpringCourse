package ru.lapshina.product;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Product> findAll();
    Optional<Product> findById(int id);

}