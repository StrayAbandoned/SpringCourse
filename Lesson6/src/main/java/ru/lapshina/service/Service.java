package ru.lapshina.service;


import ru.lapshina.entity.Product;
import ru.lapshina.entity.User;

import java.util.List;

public interface Service {
    public List<User> getProductListOfUsers(int id);
    public List<Product> getUserListOfProducts(int id);
    public  Product findProductById(int id);
    public User findUserById(int id);
    public  void insertOrUpdateProduct(Product product);
    public  void insertOrUpdateUser(User user);
    public void deleteProduct(int id);
    public void deleteUser(int id);


}
