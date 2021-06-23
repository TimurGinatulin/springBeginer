package home.mvc.MVC.repositories;

import home.mvc.MVC.models.Product;

import java.util.List;

public interface ProductRepositoryImp {
    List<Product> getAllProductList();

    Product getProductById(int id);

    Product getProductByTitle(String title);

    int getCountProduct(int id);

    int getCountProduct(String title);

    void addProduct(Product product);

    void addProduct(String title, int cost);

    void updateCost(String title, int newCost);

    void updateCost(int id, int newCost);

    void updateTitle(int id, String newTitle);

    void updateCount(String title, int count);

    void updateCount(int id, int count);

    void deleteProductByTitle(String title);

    void deleteProductById(int id);

    boolean addCountProduct(String title, int count);

    boolean decrementingCountProduct(int id, int count);

    boolean decrementingCountProduct(String title, int count);

    boolean incrementingCountProduct(int id, int count);

    boolean incrementingCountProduct(String title, int count);
}
