package home.ginatulin.repositories;

import home.ginatulin.models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component()
@Scope(scopeName = "singleton")
class ProductInMemoryRepository implements ProductRepositoryImp {
    private static List<Product> list;
    private static int id;

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        list.add(Product.builder()
                .id(0)
                .title("Apple")
                .cost(5)
                .count(2)
                .build());
        list.add(Product.builder()
                .id(1)
                .title("Orange")
                .cost(10)
                .count(5)
                .build());
        id = 2;
    }

    @PreDestroy
    public void destroy() {
        list.clear();
    }

    public List<Product> getAllProductList() {
        return Collections.unmodifiableList(list);
    }

    public Product getProductById(int id) {
        for (Product product : list) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByTitle(String title) {
        for (Product product : list) {
            if (product.getTitle().equals(title)) {
                return product;
            }
        }
        return null;
    }

    public int getCountProduct(String title) {
        Product product = getProductByTitle(title);
        int count = 0;
        if (product != null) {
            count = product.getCount();
        }
        return count;
    }

    public int getCountProduct(int id) {
        Product product = getProductById(id);
        int count = 0;
        if (product != null) {
            count = product.getCount();
        }
        return count;
    }

    public void addProduct(Product product) {
        product.setId(id);
        list.add(product);
        id++;
    }

    public void addProduct(String title, int cost) {
        list.add(Product.builder()
                .id(id)
                .title(title)
                .cost(cost)
                .build());
        id++;
    }

    public void updateCost(String title, int newCost) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                Product product = list.get(i);
                product.setCost(newCost);
                list.remove(i);
                list.add(product);
            }
        }
    }

    public void updateCost(int id, int newCost) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                Product product = list.get(i);
                product.setCost(newCost);
                list.remove(i);
                list.add(product);
            }
        }
    }

    public void updateTitle(int id, String newTitle) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                Product product = list.get(i);
                product.setTitle(newTitle);
                list.remove(i);
                list.add(product);
            }
        }
    }

    public void updateCount(String title, int count) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                Product product = list.get(i);
                product.setCount(count);
                list.remove(i);
                list.add(product);
            }
        }
    }

    public void updateCount(int id, int count) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                Product product = list.get(i);
                product.setCount(count);
                list.remove(i);
                list.add(product);
            }
        }
    }

    public void deleteProductByTitle(String title) {
        list.removeIf(x -> x.getTitle().equals(title));
    }

    public void deleteProductById(int id) {
        list.removeIf(x -> x.getId() == id);
    }

    public boolean addCountProduct(String title, int count) {
        boolean productFound = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                Product product = list.get(i);
                product.setCount(product.getCount() + count);
                list.set(i, product);
                productFound = true;
            }
        }
        return productFound;
    }

    public boolean decrementingCountProduct(String title, int count) {
        Product product = getProductByTitle(title);
        if (product != null) {
            if (count < product.getCount()) {
                updateCount(title, (getCountProduct(title) - count));
                return true;
            }
        }
        return false;
    }

    public boolean decrementingCountProduct(int id, int count) {
        Product product = getProductById(id);
        if (product != null) {
            if (count < product.getCount()) {
                updateCount(id, (getCountProduct(id) - count));
                return true;
            }
        }
        return false;
    }

    public boolean incrementingCountProduct(String title, int count) {
        Product product = getProductByTitle(title);
        if (product != null) {
            if (count < product.getCount()) {
                updateCount(title, (getCountProduct(title) + count));
                return true;
            }
        }
        return false;
    }

    public boolean incrementingCountProduct(int id, int count) {
        Product product = getProductById(id);
        if (product != null) {
            if (count < product.getCount()) {
                updateCount(id, (getCountProduct(id) + count));
                return true;
            }
        }
        return false;
    }
}
