package com.adam.buzas.onlab.main.dto;

import com.adam.buzas.onlab.main.model.Cart;
import com.adam.buzas.onlab.main.model.ShippingType;
import lombok.Data;

@Data
public class OrderRequest {
    private String address;
    private String username;
    private Cart cart;
    private ShippingType shippingType;
}