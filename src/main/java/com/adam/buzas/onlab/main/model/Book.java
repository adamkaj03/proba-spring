package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * A webshopban megvásárolható könyv entitásokat reprezentálja.
 */
@Entity
@Table(name = "konyv")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cim")
    private String title;

    @Column(name = "szerzo")
    private String author;

    @Column(name = "kiadasEve")
    private int publishYear;

    @Column(name = "ar")
    private int price;

    @ManyToOne
    @JoinColumn(name = "kategoria_id")
    private Category category;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

    public Book(String title, String author, int publishYear, int price, Category category, String imgUrl, String description) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.price = price;
        this.category = category;
        this.imgUrl = imgUrl;
        this.description = description;
    }

}
