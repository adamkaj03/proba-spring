package com.adam.buzas.onlab.main.converter;

import com.adam.buzas.onlab.main.dto.OrderDTO;
import com.adam.buzas.onlab.main.dto.UserDTO;
import com.adam.buzas.onlab.main.model.Order;
import com.adam.buzas.onlab.main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToDTOConverter implements Converter<Order, OrderDTO> {

    @Autowired
    Converter<User, UserDTO> converter;
    @Override
    public OrderDTO convert(Order source) {
        return new OrderDTO(source.getId(), source.getDateTime(), source.getDeliveryAddress(), converter.convert(source.getUser()), source.getOrderedBooks(), source.getShippingType(), source.getPrice());
    }
}
