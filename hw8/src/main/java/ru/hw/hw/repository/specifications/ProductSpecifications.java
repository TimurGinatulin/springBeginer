package ru.hw.hw.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.hw.hw.models.entitys.ProductEntity;

import java.util.Objects;

public class ProductSpecifications {
    private static Specification<ProductEntity> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<ProductEntity> priceLesserOrEqualsThan(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<ProductEntity> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<ProductEntity> build(MultiValueMap<String, String> params) {
        Specification<ProductEntity> spec = Specification.where(null);
        if (params.containsKey("min_price") &&
                !Objects.requireNonNull(params.getFirst("min_price")).isBlank()) {
            spec = spec
                    .and(ProductSpecifications.priceGreaterOrEqualsThan
                            (Integer.parseInt(Objects.requireNonNull(params.getFirst("min_price")))));
        }
        if (params.containsKey("max_price") &&
                !Objects.requireNonNull(params.getFirst("max_price")).isBlank()) {
            spec = spec
                    .and(ProductSpecifications.priceLesserOrEqualsThan
                            (Integer.parseInt(Objects.requireNonNull(params.getFirst("max_price")))));
        }
        if (params.containsKey("title") && !Objects.requireNonNull(params.getFirst("title")).isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
