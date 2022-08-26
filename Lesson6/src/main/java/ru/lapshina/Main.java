package ru.lapshina;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lapshina.config.MyConfig;
import ru.lapshina.entity.Product;
import ru.lapshina.entity.User;
import ru.lapshina.service.MyService;
import ru.lapshina.service.Service;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        MyService service = context.getBean("myService", MyService.class);

        //**** Этот блок для заполнения данными таблиц, мне через джава код это делать удобнее.
//        Session session = service.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        User ch1 = new User("Ivan");
//        User ch2 = new User("Petr");
//        User ch3 = new User("Olga");
//        User ch4 = new User("Anna");
//        User ch5 = new User("Oleg");
//        Product p1 = new Product("Pineapple", 100);
//        Product p2 = new Product("Orange", 100);
//        Product p3 = new Product("Kiwi", 100);
//        Product p4 = new Product("Potato", 100);
//        Product p5 = new Product("Bread", 100);
//        p1.addUser(ch1);
//        p1.addUser(ch2);
//        p2.addUser(ch1);
//        p2.addUser(ch2);
//        p2.addUser(ch3);
//        p3.addUser(ch2);
//        p3.addUser(ch4);
//        p3.addUser(ch5);
//        p4.addUser(ch1);
//        p4.addUser(ch4);
//        p4.addUser(ch5);
//        p4.addUser(ch3);
//        p5.addUser(ch1);
//        session.persist(p1);
//        session.persist(p2);
//        session.persist(p3);
//        session.persist(p4);
//        session.persist(p5);
//        session.getTransaction().commit();


        System.out.println(service.findProductById(1));
        System.out.println("**********************************************************************");

        System.out.println(service.findAllProducts());
        System.out.println("**********************************************************************");

        Product p1 = new Product("Orange", 300);
        Product p2 = new Product("Milk", 200);
        service.insertOrUpdateProduct(p1);
        service.insertOrUpdateProduct(p2);
        System.out.println(service.findAllProducts());
        System.out.println("**********************************************************************");

        System.out.println(service.getProductListOfUsers(1));
        System.out.println("**********************************************************************");

        service.deleteProduct(6);
        System.out.println(service.findAllProducts());

        System.out.println(service.findUserById(1));
        System.out.println("**********************************************************************");

        System.out.println(service.findAllUsers());
        System.out.println("**********************************************************************");

        User u1 = new User("Kisa");
        service.insertOrUpdateUser(u1);
        System.out.println(service.findAllUsers());
        User u2 = service.findUserById(6);
        u2.setName("NewKisa");
        service.insertOrUpdateUser(u2);
        System.out.println(service.findAllUsers());
        System.out.println("**********************************************************************");

        service.deleteUser(6);
        System.out.println(service.findAllUsers());
        System.out.println("**********************************************************************");
        System.out.println(service.getUserListOfProducts(2));


    }
}
