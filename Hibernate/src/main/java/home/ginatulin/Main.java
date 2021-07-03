package home.ginatulin;

import home.ginatulin.configuration.Configuration;
import home.ginatulin.services.WatchService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Configuration.class);
        WatchService service = context.getBean(WatchService.class);
        service.showShopCart(100L);
        service.showBuyerProduct(1L);
        service.getPriceForBuyer(3l, 1l);
    }
}
