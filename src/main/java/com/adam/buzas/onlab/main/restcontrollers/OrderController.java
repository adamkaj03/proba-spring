package com.adam.buzas.onlab.main.restcontrollers;

import com.adam.buzas.onlab.main.dto.OrderDTO;
import com.adam.buzas.onlab.main.dto.OrderRequest;
import com.adam.buzas.onlab.main.model.Cart;
import com.adam.buzas.onlab.main.model.Order;
import com.adam.buzas.onlab.main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    Converter<Order, OrderDTO> converter;

    @GetMapping("/rendelesek")
    public List<OrderDTO> getAllOrders(){
        List<OrderDTO> list = new ArrayList<>();
        for (Order o : orderService.getAllOrders()){
            list.add(converter.convert(o));
        }
        return list;
    }

    @GetMapping("/rendelesek/{id}")
    public OrderDTO getOrderById(@PathVariable("id") int id){
        return converter.convert(orderService.getOrder(id).get());
    }

    @PostMapping("/rendelesek")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        System.out.println(request.getShippingType().getName());
        orderService.newOrder(LocalDateTime.now(), request.getAddress(), request.getUsername(), request.getCart(), request.getShippingType());
        return ResponseEntity.ok("Siker");
    }

//    @PutMapping("/rendelesek/{id}")
//    public OrderDTO updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
//        order.setId(id);
//        Order o = orderService.newOrder(order.getDateTime(), order.getDeliveryAddress(), order.getUser());
//        return converter.convert(o);
//    }

    @DeleteMapping("/rendelesek/{id}")
    public void deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
    }
}
