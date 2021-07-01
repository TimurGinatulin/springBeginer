package home.ginatulin;

import home.ginatulin.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class CRUD {
    private static SessionFactory sessionFactory;

    private static void init() {
        System.out.println("Initialization ...");
        sessionFactory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            String sql = Files.lines(Paths.get("Hibernate/full.sql"))
                    .collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Initialization done.");
        } catch (IOException e) {
            System.out.println("Faulted");
        }
    }

    private static void destroy() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        init();
        addProduct("Potatoes", 7);
        getAllProduct();
        getProductById(1L);
        getProductByTitle("Kiwi");
        updateCost(1L, 1000);
        updateCost(1L, "NewTitle");
        deleteProduct(1L);
        destroy();
    }

    //Create
    private static void addProduct(String title, int cost) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new ProductEntity(title, cost));
            session.getTransaction().commit();
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }

    //Read
    public static void getAllProduct() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<ProductEntity> items = session.createQuery("from ProductEntity").getResultList();
            System.out.println(items + "\n");
            session.getTransaction().commit();
        }
    }

    private static void getProductById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session
                    .createQuery("select p from ProductEntity p where p.id = " + id, ProductEntity.class)
                    .getSingleResult();
            System.out.println(product.toString());
            session.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }

    private static void getProductByTitle(String title) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session
                    .createQuery("select s from ProductEntity s where s.title = '" + title + "'", ProductEntity.class)
                    .getSingleResult();
            System.out.println(product.toString());
            session.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }

    //Update
    private static void updateCost(Long id, int cost) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            product.setCost(cost);
            session.update(product);
            session.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }

    private static void updateCost(Long id, String title) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            product.setTitle(title);
            session.update(product);
            session.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }

    //Delete
    private static void deleteProduct(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            session.delete(product);
            session.getTransaction().commit();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Faulted");
        }
    }
}
