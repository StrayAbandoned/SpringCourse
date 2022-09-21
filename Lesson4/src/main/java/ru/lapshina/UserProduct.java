package ru.lapshina;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(UserProductPK.class)
@Table(name = "user_product")
public class UserProduct {
    @Id
    @Column(name = "user_name")
    String username;
    @Id
    @Column(name = "product_id")
    Long productId;

}

class UserProductPK implements Serializable {
    protected String username;
    protected Long productId;

    public UserProductPK() {
    }

    public UserProductPK(String username, Long productId) {
        this.username = username;
        this.productId = productId;
    }
}
