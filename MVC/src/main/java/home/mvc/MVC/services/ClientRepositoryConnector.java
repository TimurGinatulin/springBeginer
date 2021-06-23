package home.mvc.MVC.services;

import home.mvc.MVC.models.Product;
import home.mvc.MVC.repositories.ProductRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class ClientRepositoryConnector {
    @Autowired
    private ProductRepositoryImp repositoryImp;

    protected List<Product> getAllProductList() {
        return repositoryImp.getAllProductList();
    }

    protected Product getProductById(int id) {
        return repositoryImp.getProductById(id);
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
