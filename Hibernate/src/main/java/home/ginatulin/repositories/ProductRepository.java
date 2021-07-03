package home.ginatulin.repositories;

import home.ginatulin.entity.Product;
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
public class ProductRepository implements RepositoryImp {
    @Autowired
    private DBConnector dateBase;

    //create
    public void create(EntityImp entity) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    //Read
    @Override
    public List<EntityImp> getList() {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            List<Product> list = session.createQuery("from products").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(list);
        }
    }

    @Override
    public Optional<EntityImp> getObjectById(Long id) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(product);
        }
    }

    //Update
    @Override
    public void update(Long id, int cost) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setCost(cost);
            session.update(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Long id, String name) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setName(name);
            session.update(product);
            session.getTransaction().commit();
        }
    }
    //Delete

    @Override
    public void delete(Long id) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Optional<Double> getPriceForBuyer(Long productId, Long buyerId) {
        try (Session session = dateBase.getCurrentSession()) {
            session.beginTransaction();
            double price = (double) session
                    .createSQLQuery("Select price from product_buyer " +
                            "where idBuyer = " + buyerId + " and idProduct = " + productId)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(price);
        }
    }
}
