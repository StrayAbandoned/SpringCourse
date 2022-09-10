package ru.lapshina.service;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lapshina.product.ProductDto;
import ru.lapshina.product.ProductRepository;
import ru.lapshina.product.QProduct;
import ru.lapshina.product.mapper.ProductMapperImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapperImpl mapper;

    public Optional<ProductDto> findById(Long id) {
        return repository.findById(id).map(mapper::mapToProductDto);
    }

    public void save(ProductDto productDto) {
        repository.save(mapper.mapToProduct(productDto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ProductDto> showAll(Long minCost, Long maxCost) {
        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();
        if (minCost != null) {
            predicate.and(product.cost.goe(minCost));
        }
        if (maxCost != null) {
            predicate.and(product.cost.loe(maxCost));
        }
        return StreamSupport.stream(repository.findAll(predicate).spliterator(), true)
                .map(mapper::mapToProductDto).toList();
    }

    public Page<ProductDto> findPaginated(int page, int size, Long minCost, Long maxCost) {
        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();
        if (minCost != null) {
            predicate.and(product.cost.goe(minCost));
        }
        if (maxCost != null) {
            predicate.and(product.cost.loe(maxCost));
        }
        return repository.findAll(predicate, PageRequest.of(page, size)).map(mapper::mapToProductDto);
    }


}

