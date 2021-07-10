package ru.hw.hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hw.hw.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByCostLessThan(int max);

    Optional<List<Product>> findByCostGreaterThan(int min);

    Optional<List<Product>> findByCostBetween(int min, int max);

    Optional<List<Product>> findByNameLike(String name);
}
