package home.ginatulin.repositories;

import home.ginatulin.entity.Buyer;
import home.ginatulin.entity.implementations.EntityImp;
import home.ginatulin.repositories.DBConnector.implementations.DBConnector;
import home.ginatulin.repositories.Implementations.RepositoryImp;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class BuyerRepository implements RepositoryImp {
    @Autowired
    private DBConnector dateBase;

    @Override
    public void create(EntityImp entity) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<EntityImp> getList() {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            List<Buyer> list = session.createQuery("from buyer").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(list);
        }
    }

    @Override
    public Optional<EntityImp> getObjectById(Long id) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(buyer);
        }
    }

    @Override
    public void update(Long id, int cost) {

    }

    @Override
    public void update(Long id, String name) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            buyer.setName(name);
            session.update(buyer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            session.delete(buyer);
            session.getTransaction().commit();
        }
    }
}
