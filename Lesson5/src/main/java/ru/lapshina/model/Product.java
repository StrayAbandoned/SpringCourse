package ru.lapshina.model;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title" , nullable = false, unique = true)
    private String title;

    @Column(name = "cost", nullable = false)
    private int cost;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }
}