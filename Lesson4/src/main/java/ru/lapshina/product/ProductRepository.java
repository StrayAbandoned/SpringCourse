package ru.lapshina.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE cost >= :minCost AND cost <= :maxCost", nativeQuery = true)
    public List<Product> betweenCostFilter(Long minCost, Long maxCost);

    public List<Product> findAllByCostIsLessThanEqual(Long maxCost);

    public List<Product> findAllByCostIsGreaterThanEqual(Long minCost);

}