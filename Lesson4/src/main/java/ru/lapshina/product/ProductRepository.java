package ru.lapshina.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_product VALUES (:name, :id, 1)", nativeQuery = true)
    void addToCart(String name, Long id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_product SET quality =:count where user_name=:name and product_id=:id", nativeQuery = true)
    void addToCart(String name, Long id, Integer count);

    @Query("from Product p join UserProduct u on u.productId=p.id where u.username=:name")
    List<Product> getListOfProducts(String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE from user_product u WHERE u.product_id=:id AND u.user_name=:currentUserName", nativeQuery = true)
    void removeFromCart(String currentUserName, Long id);

    @Query(value = "SELECT quality FROM user_product u where u.user_name=:name and u.product_id=:id", nativeQuery = true)
    Integer getCountOfProducts(String name, Long id);
}