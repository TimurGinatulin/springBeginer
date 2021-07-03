package home.ginatulin.repositories.Implementations;

import home.ginatulin.entity.implementations.EntityImp;

import java.util.List;
import java.util.Optional;

public interface RepositoryImp {
    void create(EntityImp entity);

    List<EntityImp> getList();

    Optional<EntityImp> getObjectById(Long id);

    void update(Long id, int cost);

    void update(Long id, String name);

    void delete(Long id);
}
