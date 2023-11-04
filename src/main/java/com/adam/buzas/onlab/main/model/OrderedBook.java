package com.adam.buzas.onlab.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A rendelt könyveket reprezentáló entitás.
 */

@Entity
@Data
@Table(name = "rendelt_konyv")
@NoArgsConstructor
public class OrderedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rendeles_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "konyv_id")
    private Book book;

    @Column(name = "db")
    private int count;

    public OrderedBook(Order order, Book book, int count) {
        this.order = order;
        this.book = book;
        this.count = count;
    }
}
