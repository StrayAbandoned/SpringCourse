package ru.lapshina.model;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;



import java.util.List;


public class ProductRepository {
    EntityManagerFactory factory;
    EntityManager manager;
//    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
//    Session session = sessionFactory.getCurrentSession();


    public ProductRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        manager = factory.createEntityManager();
        insert(List.of(new Product("Pineapple", 105),
                new Product("Cucumber", 40),
                new Product("Broccoli", 180),
                new Product("Orange", 95),
                new Product("Kiwi", 120)));
    }

    public List<Product> findAll() {
        manager.getTransaction().begin();
        List<Product> products = manager.createQuery("from Product").getResultList();
        manager.getTransaction().commit();
        return products;
    }

    public Product findById(int id) {
        manager.getTransaction().begin();
        Product product = manager.find(Product.class, id);
        manager.getTransaction().commit();
        return product;
    }

    public void insert(Product product) {
        manager.getTransaction().begin();
        manager.persist(product);
        manager.getTransaction().commit();
    }

    public void insert(List<Product> products){
        manager.getTransaction().begin();
        for(Product p: products){
            manager.persist(p);
        }
        manager.getTransaction().commit();
    }

    public void delete(Product product) {
        manager.getTransaction().begin();
        manager.remove(product);
        manager.getTransaction().commit();
    }

    public void close() {
        factory.close();
        manager.close();
    }

}