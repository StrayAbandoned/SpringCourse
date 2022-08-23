package ru.lapshina.product;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class ProductRepository implements Repository{

    public ProductRepository() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        insert(List.of(new Product("Pineapple", 105),
                new Product("Cucumber", 40),
                new Product("Broccoli", 180),
                new Product("Orange", 95),
                new Product("Kiwi", 120)));
    }

    public List<Product> findAll() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        manager.beginTransaction();
        List<Product> products = manager.createQuery("from Product").getResultList();
        manager.getTransaction().commit();
        return products;
    }

    public Product findById(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        manager.beginTransaction();
        Product product = manager.find(Product.class, id);
        manager.getTransaction().commit();
        return product;
    }

    public void insert(Product product) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        manager.beginTransaction();
        manager.saveOrUpdate(product);
        manager.getTransaction().commit();
    }

    public void insert(List<Product> products){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        manager.beginTransaction();
        for(Product p: products){
            manager.saveOrUpdate(p);
        }
        manager.getTransaction().commit();
    }

    public void delete(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        Session manager = sessionFactory.getCurrentSession();
        manager.beginTransaction();
        Product product = findById(id);
        manager.remove(product);
        manager.getTransaction().commit();
    }

//    public void close() {
//        sessionFactory.close();
//        manager.close();
//    }

}