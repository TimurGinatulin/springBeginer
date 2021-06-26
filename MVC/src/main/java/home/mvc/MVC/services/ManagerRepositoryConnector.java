package home.mvc.MVC.services;

import home.mvc.MVC.exceptions.ResourcesNotFoundException;
import home.mvc.MVC.models.Product;
import home.mvc.MVC.repositories.ProductRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

class ManagerRepositoryConnector {
    @Autowired
    private ProductRepositoryImp repositoryImp;

    protected List<Product> getAllProductList() {
        return repositoryImp.getAllProductList();
    }

    protected Product getProductById(int id) {
        return repositoryImp.getProductById(id).orElseThrow(()->new ResourcesNotFoundException("Product doesn't found"));
    }

    protected Product getProductByTitle(String title) {
        return repositoryImp.getProductByTitle(title);
    }

    protected int getCountProduct(String title) {
        return repositoryImp.getCountProduct(title);
    }

    protected int getCountProduct(int id) {
        return repositoryImp.getCountProduct(id);
    }

    protected void addProduct(Product product) {
        repositoryImp.addProduct(product);
    }

    protected void addProduct(String title, int cost) {
        repositoryImp.addProduct(title, cost);
    }

    protected void updateCost(String title, int newCost) {
        repositoryImp.updateCost(title, newCost);
    }

    protected void updateCost(int id, int newCost) {
        repositoryImp.updateCost(id, newCost);
    }

    protected void updateTitle(int id, String newTitle) {
        repositoryImp.updateTitle(id, newTitle);
    }

    protected void updateCount(String title, int count) {
        repositoryImp.updateCount(title, count);
    }

    protected void updateCount(int id, int count) {
        repositoryImp.updateCount(id, count);
    }

    protected void deleteProductByTitle(String title) {
        repositoryImp.deleteProductByTitle(title);
    }

    protected void deleteProductById(int id) {
        repositoryImp.deleteProductById(id);
    }

    protected boolean addCountProduct(String title, int count) {
        return repositoryImp.addCountProduct(title, count);
    }

    protected boolean decrementingCountProduct(int id, int count) {
        return repositoryImp.decrementingCountProduct(id, count);
    }

    protected boolean decrementingCountProduct(String title, int count) {
        return repositoryImp.decrementingCountProduct(title, count);
    }

    protected boolean incrementingCountProduct(int id, int count) {
        return repositoryImp.incrementingCountProduct(id, count);
    }

    protected boolean incrementingCountProduct(String title, int count) {
        return repositoryImp.incrementingCountProduct(title, count);
    }

}
