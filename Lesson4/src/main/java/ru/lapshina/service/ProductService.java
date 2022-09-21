package ru.lapshina.service;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lapshina.product.ProductDto;
import ru.lapshina.product.ProductRepository;
import ru.lapshina.product.QProduct;
import ru.lapshina.product.mapper.ProductMapperImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Getter
@Setter
@AllArgsConstructor
@Service
@Slf4j
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


    public void addToCart(String name, Long id) {
        Integer count = Optional.ofNullable(repository.getCountOfProducts(name, id)).orElse(0);
        log.info(String.valueOf(count));
        if(count==0){
            log.info("addedtocart");
            repository.addToCart(name, id);
        } else{
            count++;
            log.info("updatedtocart");
            repository.addToCart(name, id, count);
        }
    }

    public Map<ProductDto, Integer> showCart(String currentUserName) {
        Map<ProductDto, Integer> products = new HashMap<>();
        List<ProductDto> listProduct= StreamSupport.stream(repository.getListOfProducts(currentUserName).spliterator(), true)
                .map(mapper::mapToProductDto).toList();
        for(ProductDto p: listProduct){
            products.put(p, repository.getCountOfProducts(currentUserName, p.getId()));
        }
        return products;
    }

    public void removeFromCart(String currentUserName, Long id) {
        repository.removeFromCart(currentUserName, id);
    }
}

