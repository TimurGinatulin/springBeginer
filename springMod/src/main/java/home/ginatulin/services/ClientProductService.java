package home.ginatulin.services;

import home.ginatulin.models.Product;
import home.ginatulin.services.implementations.ProductServiceImp;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ClientProductService extends ClientRepositoryConnector implements ProductServiceImp {
    private static List<Product> shoppingCart;

    @PostConstruct
    public void init() {
        shoppingCart = new ArrayList<>();
    }

    @PreDestroy
    public void destroy() {
        shoppingCart.clear();
    }

    public List<Product> getShoppingCart() {
        return Collections.unmodifiableList(shoppingCart);
    }

    public int getCartCount() {
        int count = 0;
        for (Product product : shoppingCart) {
            count += product.getCount();
        }
        return count;
    }

    public Product getProductInShoppingCart(String title) {
        Product product = null;
        for (Product value : shoppingCart) {
            if (value.getTitle().equals(title)) {
                product = value;
            }
        }
        return product;
    }

    public Product getProductInShoppingCart(int id) {
        Product product = null;
        for (Product value : shoppingCart) {
            if (value.getId() == id) {
                product = value;
            }
        }
        return product;
    }

    public double averagePriceAtCart() {
        double sum = 0.00;
        int count = 0;
        for (Product product : shoppingCart) {
            sum += product.getCost() * product.getCount();
            count += product.getCount();
        }
        return sum / count;
    }

    public boolean addToShoppingCart(String productTitle, int count) {
        if (getProductByTitle(productTitle) != null) {
            Product addingProduct = getProductByTitle(productTitle);
            addingProduct.setCount(count);
            shoppingCart.add(addingProduct);
            return decrementingCountProduct(productTitle, count);
        } else return false;
    }

    public boolean addToShoppingCart(int productId, int count) {
        Product product = getProductById(productId);
        if (product != null) {
            shoppingCart.add(product);
            return decrementingCountProduct(productId, count);
        } else return false;
    }

    public boolean deleteFromShoppingCart(String title) {
        Product product = getProductInShoppingCart(title);
        if (product == null) {
            return false;
        } else {
            shoppingCart.remove(product);
            return true;
        }
    }

    public boolean deleteFromShoppingCart(int id) {
        Product product = getProductInShoppingCart(id);
        if (product == null) {
            return false;
        } else {
            shoppingCart.remove(product);
            return true;
        }
    }

    public boolean deleteFromShoppingCart(int id, int count) {
        Product product = getProductInShoppingCart(id);
        if (product != null) {
            if (product.getCount() < count) {
                shoppingCart.remove(product);
                product.setCount(product.getCount() - count);
                shoppingCart.add(product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFromShoppingCart(String title, int count) {
        Product product = getProductInShoppingCart(title);
        if (product != null) {
            if (product.getCount() < count) {
                shoppingCart.remove(product);
                product.setCount(product.getCount() - count);
                shoppingCart.add(product);
                return true;
            }
        }
        return false;
    }

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
    protected int getCountProduct(String title) {
        return super.getCountProduct(title);
    }

    @Override
    protected int getCountProduct(int id) {
        return super.getCountProduct(id);
    }

    @Override
    protected boolean decrementingCountProduct(int id, int count) {
        return super.decrementingCountProduct(id, count);
    }

    @Override
    protected boolean decrementingCountProduct(String title, int count) {
        return super.decrementingCountProduct(title, count);
    }

    @Override
    protected boolean incrementingCountProduct(int id, int count) {
        return super.incrementingCountProduct(id, count);
    }

    @Override
    protected boolean incrementingCountProduct(String title, int count) {
        return super.incrementingCountProduct(title, count);
    }
}
