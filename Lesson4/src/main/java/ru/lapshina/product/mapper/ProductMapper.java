package ru.lapshina.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {
    Product mapToProduct(ProductDto productDto);

    ProductDto mapToProductDto(Product product);
}
