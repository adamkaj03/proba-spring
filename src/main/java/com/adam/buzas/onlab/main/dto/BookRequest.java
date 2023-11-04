package com.adam.buzas.onlab.main.dto;

import com.adam.buzas.onlab.main.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private int publishYear;
    private int price;
    private Category category;
    private String description;
}
