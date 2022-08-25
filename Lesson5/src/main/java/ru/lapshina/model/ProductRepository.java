package ru.lapshina.model;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;



import java.util.List;


public class ProductRepository {
    EntityManagerFactory factory;
    EntityManager manager;


    public ProductRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        manager = factory.createEntityManager();
        insertOrUpdate(List.of(new Product("Pineapple", 105),
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

    public void insertOrUpdate(Product product) {
        manager.getTransaction().begin();
        List<Product> p = manager.createQuery("from Product where title = '" + product.getTitle()+"'").getResultList();
        if(p.size()!=0){
            product.setId(p.get(0).getId());
            manager.merge(product);
        } else {
            manager.persist(product);
        }

        manager.getTransaction().commit();
    }

    public void insertOrUpdate(List<Product> products){
        manager.getTransaction().begin();

        for(Product p: products){
            List<Product> p0 = manager.createQuery("from Product where title = '" + p.getTitle()+"'").getResultList();
            if(p0.size()==0){
                manager.persist(p);
            } else {
                p.setId(p0.get(0).getId());
                manager.merge(p);;
            }
        }
        manager.getTransaction().commit();
    }

    public void delete(int id) {
        manager.getTransaction().begin();
        Product product = manager.find(Product.class,1);
        if(product!=null){
            manager.remove(product);
        }

        manager.getTransaction().commit();
    }

    public void close() {
        factory.close();
        manager.close();
    }

}