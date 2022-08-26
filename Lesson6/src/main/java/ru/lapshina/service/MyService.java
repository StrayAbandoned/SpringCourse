package ru.lapshina.service;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lapshina.dao.ProductDao;
import ru.lapshina.dao.UserDao;
import ru.lapshina.entity.Product;
import ru.lapshina.entity.User;

import java.util.List;

@org.springframework.stereotype.Service
@Getter
@NoArgsConstructor
public class MyService implements Service {
    public final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).addAnnotatedClass(User.class).buildSessionFactory();
    Session session;

    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;

    //проверено
    @Override
    @Transactional
    public List<User> getProductListOfUsers(int id) {
        session = sessionFactory.getCurrentSession();
        return productDao.getUsers(id);

    }


    public List<Product> findAllProducts() {
        session = sessionFactory.getCurrentSession();
        return productDao.findAll();

    }


    public List<User> findAllUsers() {
        session = sessionFactory.getCurrentSession();
        return userDao.findAll();

    }

    @Override
    public List<Product> getUserListOfProducts(int id) {
        session = sessionFactory.getCurrentSession();
        return userDao.getProducts(id);
    }


    @Override
    public Product findProductById(int id) {
        session = sessionFactory.getCurrentSession();
        return productDao.findById(id);
    }


    @Override
    public User findUserById(int id) {
        session = sessionFactory.getCurrentSession();
        return userDao.findById(id);
    }


    @Override
    public void insertOrUpdateProduct(Product product) {
        session = sessionFactory.getCurrentSession();
        productDao.insertOrUpdate(product);

    }

    @Override
    public void insertOrUpdateUser(User user) {
        session = sessionFactory.getCurrentSession();
        userDao.insertOrUpdate(user);

    }

    @Override
    public void deleteProduct(int id) {
        session = sessionFactory.getCurrentSession();
        productDao.delete(id);

    }

    @Override
    public void deleteUser(int id) {
        session = sessionFactory.getCurrentSession();
        userDao.delete(id);

    }
}
