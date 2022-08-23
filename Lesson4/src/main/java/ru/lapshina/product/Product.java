package ru.lapshina.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Locale;


@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer id;
    @NotBlank(message = "Field mustn't be empty")
    private String title;

    @Positive(message = "Cost must be more than zero")
    private int cost;


    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }


}