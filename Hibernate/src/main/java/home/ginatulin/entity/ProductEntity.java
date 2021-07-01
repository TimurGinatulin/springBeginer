package home.ginatulin.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;

    public ProductEntity() {
    }

    public ProductEntity(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public ProductEntity(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
