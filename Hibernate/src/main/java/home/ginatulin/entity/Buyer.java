package home.ginatulin.entity;

import home.ginatulin.entity.implementations.EntityImp;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "buyer")
public class Buyer implements EntityImp {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_buyer",
            joinColumns = @JoinColumn(name = "idBuyer"),
            inverseJoinColumns = @JoinColumn(name = "idProduct")
    )
    private List<Product> shopCart;

    public Buyer() {
    }

    public Buyer(String name) {
        this.name = name;
    }

    public Buyer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
