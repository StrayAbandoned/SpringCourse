package ru.lapshina.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "username")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean isEnabled;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public User(String name, String password, boolean isEnabled) {
        this.name = name;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

}