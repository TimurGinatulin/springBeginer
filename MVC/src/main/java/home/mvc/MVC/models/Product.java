package home.mvc.MVC.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Builder
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Product {
    private int id;
    private String title;
    private int cost;
    private int count;
}
