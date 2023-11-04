package com.adam.buzas.onlab.main.model;

import com.adam.buzas.onlab.main.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A rendeléseket reprezentáló entitás.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "rendeles")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idopont")
    private LocalDateTime dateTime;

    @Column(name = "szallitasi_cim")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "felhasznalo_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "szallitas_tipusok_id")
    private ShippingType shippingType;


    @OneToMany(mappedBy="order", fetch = FetchType.LAZY)
    private List<OrderedBook> orderedBooks;

    @Column(name = "ar")
    private int price;


    public Order(LocalDateTime dateTime, String deliveryAddress, User user, ShippingType shippingType, int price) {
        this.dateTime = dateTime;
        this.deliveryAddress = deliveryAddress;
        this.user = user;
        this.shippingType = shippingType;
        this.price = price;
    }

}
