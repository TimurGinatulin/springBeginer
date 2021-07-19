package ru.hw.hw.models.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Where(clause = "time_delete is null")
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private int price;
    @OneToOne
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_category", referencedColumnName = "id")})
    private CategoryEntity category;
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;
    @Column(name = "time_update")
    private LocalDateTime timeUpdate;
    @Column(name = "time_delete")
    private LocalDateTime timeDelete;

    public ProductEntity(String title, int price) {
        this.name = title;
        this.price = price;
    }
}
