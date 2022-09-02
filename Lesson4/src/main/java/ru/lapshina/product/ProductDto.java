package ru.lapshina.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "Cannot be empty!")
    private String title;

    @Positive(message = "Must be more than zero!")
    @NotNull(message = "Cannot be empty!")
    private Long cost;
}
