package home.ginatulin.entity;

import home.ginatulin.entity.implementations.EntityImp;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "products")
public class Product implements EntityImp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String name;
    @Column(name = "cost")
    private int cost;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_buyer",
            joinColumns = @JoinColumn(name = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idBuyer"))
    private List<Buyer> buyers;

    public Product() {
    }

    public Product(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Product(Long id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
