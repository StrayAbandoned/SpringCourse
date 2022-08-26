package ru.lapshina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.lapshina.entity.Product;
import ru.lapshina.entity.User;
import ru.lapshina.service.MyService;

import java.util.List;

@Repository
public class UserDao implements Dao<User>{
    @Autowired
    MyService service;
    @Override
    public List<User> findAll() {
        service.getSession().beginTransaction();
        List<User> users = service.getSession().createQuery("from User").getResultList();
        service.getSession().getTransaction().commit();
        return users;
    }

    @Override
    public User findById(int id) {
        service.getSession().beginTransaction();
        User user = service.getSession().get(User.class, id);
        service.getSession().getTransaction().commit();

        return user;
    }

    @Override
    public void insertOrUpdate(User user) {
        service.getSession().beginTransaction();
        if(user.getId()==0){
            service.getSession().persist(user);
        } else {
            service.getSession().merge(user);
        }
        service.getSession().getTransaction().commit();

    }

    @Override
    public void delete(int id) {
        service.getSession().beginTransaction();
        User user = service.getSession().get(User.class, id);
        if(user!=null){
            service.getSession().remove(user);
        }
        service.getSession().getTransaction().commit();
    }

    public List<Product> getProducts(int id){
        List<Product> products;
        service.getSession().beginTransaction();
        User user = service.getSession().get(User.class, id);
        if(user!=null){
            products = user.getProducts();
        } else {
            products = null;
        }
        service.getSession().getTransaction().commit();
        return products;
    }
}
