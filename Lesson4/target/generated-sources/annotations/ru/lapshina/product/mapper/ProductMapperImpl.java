package ru.lapshina.product.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.lapshina.product.Product;
import ru.lapshina.product.ProductDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-13T18:47:54+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        if ( productDto.getTitle() != null ) {
            product.setTitle( productDto.getTitle() );
        }
        if ( productDto.getCost() != null ) {
            product.setCost( productDto.getCost() );
        }

        return product;
    }

    @Override
    public ProductDto mapToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }
        if ( product.getTitle() != null ) {
            productDto.setTitle( product.getTitle() );
        }
        if ( product.getCost() != null ) {
            productDto.setCost( product.getCost() );
        }

        return productDto;
    }
}
