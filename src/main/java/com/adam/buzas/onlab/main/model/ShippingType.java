package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "szallitas_tipusok")
public class ShippingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nev")
    private String name;

    @Column(name = "ar")
    private int price;
}
