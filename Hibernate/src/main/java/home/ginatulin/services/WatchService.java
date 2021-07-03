package home.ginatulin.services;

import home.ginatulin.entity.Buyer;
import home.ginatulin.entity.Product;
import home.ginatulin.exceptions.ResourcesNotFoundException;
import home.ginatulin.repositories.BuyerRepository;
import home.ginatulin.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WatchService {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private ProductRepository productRepository;

    public void showShopCart(Long id) {
        try {
            Buyer buyer = (Buyer) buyerRepository.getObjectById(id)
                    .orElseThrow(() -> new ResourcesNotFoundException("Buyer witch id " + id + " not found"));
            List<Product> shopCart = buyer.getShopCart();
            for (Product product : shopCart) {
                System.out.println(product.toString());
            }
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showBuyerProduct(Long id) {
        try {
            Product product = (Product) productRepository.getObjectById(id)
                    .orElseThrow(() -> new ResourcesNotFoundException("Product witch id " + id + " not found"));
            List<Buyer> buyers = product.getBuyers();
            for (Buyer buyer : buyers) {
                System.out.println(buyer.toString());
            }
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getPriceForBuyer(Long productId, Long buyerId) {
        try {
            System.out.println(productRepository.getPriceForBuyer(productId, buyerId)
                    .orElseThrow(() -> new ResourcesNotFoundException("Exception")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}