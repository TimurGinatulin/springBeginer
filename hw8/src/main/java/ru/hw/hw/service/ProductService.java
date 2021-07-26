package ru.hw.hw.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.exception.NotUniqueNameException;
import ru.hw.hw.models.dtos.ProductDto;
import ru.hw.hw.models.entitys.ProductEntity;
import ru.hw.hw.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public Page<ProductDto> findAll(
            Specification<ProductEntity> specification, int page, int size) {
        if (page < 0)
            page = 1;
        return repository.findAll(
                specification,
                PageRequest.of(page - 1, size))
                .map(ProductDto::new);

    }

    public Page<ProductDto> findByNameLike(Integer page, int i, String name) {
        if (page < 0)
            page = 1;
        return repository
                .findByNameLike(
                        "%" + name + "%",
                        PageRequest.of(page, i, Sort.by("id")
                                .ascending()))
                .map(ProductDto::new);
    }

    public Page<ProductDto> findByCostBetween(
            Integer page, int i, Integer minPrice, Integer maxPrice) {
        return repository
                .findByPriceBetween(minPrice, maxPrice, PageRequest.of(page, i))
                .map(ProductDto::new);
    }

    public Optional<ProductDto> findById(Integer id) {
        return repository
                .findById(id)
                .map(ProductDto::new);
    }

    public ProductDto save(ProductDto product) {
        try {
            ProductEntity entity =
                    new ProductEntity(product.getTitle(), product.getPrice());
            return mapper.map(repository.save(entity), ProductDto.class);
        } catch (RuntimeException e) {
            throw new NotUniqueNameException("Name not unique");
        }
    }

    public void delete(int id) {
        ProductEntity entity = repository
                        .findById(id)
                        .orElseThrow(()->new NotFoundException("Not found"));
        entity.setTimeDelete(LocalDateTime.now());
    }

    public ProductDto update(Integer id, String name, Integer cost) {
        ProductEntity entity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found"));
        if (name != null)
            entity.setName(name);
        if (cost != null)
            entity.setPrice(cost);
        return mapper.map(repository.save(entity), ProductDto.class);
    }
}
