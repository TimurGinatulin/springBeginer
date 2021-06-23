package home.mvc.MVC.services;

import home.mvc.MVC.models.Product;
import home.mvc.MVC.services.implementations.ProductServiceImp;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ManagerProductService extends ManagerRepositoryConnector implements ProductServiceImp {

    @Override
    public List<Product> getAllProductList() {
        return super.getAllProductList();
    }

    @Override
    public Product getProductById(int id) {
        return super.getProductById(id);
    }

    @Override
    public Product getProductByTitle(String title) {
        return super.getProductByTitle(title);
    }

    @Override
    public int getCountProduct(String title) {
        return super.getCountProduct(title);
    }

    @Override
    public int getCountProduct(int id) {
        return super.getCountProduct(id);
    }

    @Override
    public void addProduct(Product product) {
        super.addProduct(product);
    }

    @Override
    public void addProduct(String title, int cost) {
        super.addProduct(title, cost);
    }

    @Override
    public void updateCost(String title, int newCost) {
        super.updateCost(title, newCost);
    }

    @Override
    public void updateCost(int id, int newCost) {
        super.updateCost(id, newCost);
    }

    @Override
    public void updateTitle(int id, String newTitle) {
        super.updateTitle(id, newTitle);
    }

    @Override
    public void updateCount(String title, int count) {
        super.updateCount(title, count);
    }

    @Override
    public void updateCount(int id, int count) {
        super.updateCount(id, count);
    }

    @Override
    public void deleteProductByTitle(String title) {
        super.deleteProductByTitle(title);
    }

    @Override
    public void deleteProductById(int id) {
        super.deleteProductById(id);
    }

    @Override
    public boolean addCountProduct(String title, int count) {
        return super.addCountProduct(title, count);
    }

    @Override
    public boolean decrementingCountProduct(int id, int count) {
        return super.decrementingCountProduct(id, count);
    }

    @Override
    public boolean decrementingCountProduct(String title, int count) {
        return super.decrementingCountProduct(title, count);
    }

    @Override
    public boolean incrementingCountProduct(int id, int count) {
        return super.incrementingCountProduct(id, count);
    }

    @Override
    public boolean incrementingCountProduct(String title, int count) {
        return super.incrementingCountProduct(title, count);
    }
}
