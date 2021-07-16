package ru.hw.hw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hw.hw.models.entitys.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {

    Page<ProductEntity> findByCostBetween(int min, int max, Pageable pageable);

    Page<ProductEntity> findByNameLike(String name, Pageable pageable);

}
