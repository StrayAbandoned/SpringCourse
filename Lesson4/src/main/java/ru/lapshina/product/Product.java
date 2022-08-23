package ru.lapshina.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.*;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;


    @Column(name = "cost", nullable = false)
    private int cost;


    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }


}