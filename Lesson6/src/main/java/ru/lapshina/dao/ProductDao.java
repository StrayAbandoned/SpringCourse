package ru.lapshina.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lapshina.entity.Product;
import ru.lapshina.entity.User;
import ru.lapshina.service.MyService;

import java.util.List;

@Repository
@Getter
@Setter
public class ProductDao implements Dao<Product>{
    @Autowired
    MyService service;

    @Override
    public  List<Product> findAll() {
        service.getSession().beginTransaction();
        List<Product> products = service.getSession().createQuery("from Product").getResultList();
        service.getSession().getTransaction().commit();
        return products;
    }

    @Override
    public Product findById(int id) {
        service.getSession().beginTransaction();
        Product product = service.getSession().get(Product.class, id);
        service.getSession().getTransaction().commit();

        return product;

    }

    @Override
    public void insertOrUpdate(Product p) {
        service.getSession().beginTransaction();
        Product product = (Product) service.getSession().createQuery("from Product where title = '"+p.getTitle()+"'").getResultList().stream().findFirst().orElse(null);
        if(product==null){
            service.getSession().persist(p);
        } else {
            p.setId(product.getId());
            service.getSession().merge(p);
        }
        service.getSession().getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        service.getSession().beginTransaction();
        Product product = service.getSession().get(Product.class, id);
        if(product!=null){
            service.getSession().remove(product);
        }
        service.getSession().getTransaction().commit();

    }

    public List<User> getUsers(int id){
        List<User> users;
        service.getSession().beginTransaction();
        Product product = service.getSession().get(Product.class, id);
        if(product!=null){
             users = product.getUsers();
        } else {
            users = null;
        }
        service.getSession().getTransaction().commit();
        return users;
    }
}
