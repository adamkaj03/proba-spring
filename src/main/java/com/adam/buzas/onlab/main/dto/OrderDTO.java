package com.adam.buzas.onlab.main.dto;

import com.adam.buzas.onlab.main.model.OrderedBook;
import com.adam.buzas.onlab.main.model.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private LocalDateTime dateTime;
    private String deliveryAddress;
    private UserDTO userDTO;
    private List<OrderedBook> orderedBooks;
    private ShippingType shippingType;
    private int price;
}
