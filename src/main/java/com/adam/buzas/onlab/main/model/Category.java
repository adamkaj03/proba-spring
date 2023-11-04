package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A könyveknek a kategoriái, lombok segítségével van getter és settere az adattagokank
 */

@Entity
@Table(name = "kategoria")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nev")
    private String name;
}
