package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Cart;
import com.adam.buzas.onlab.main.model.ShippingType;
import com.adam.buzas.onlab.main.model.User;
import com.adam.buzas.onlab.main.model.Order;
import com.adam.buzas.onlab.main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderedBookService orderedBookService;
    @Autowired
    BookService bookService;
    @Autowired
    ShippingTypeService shippingTypeService;

    public Optional<Order> getOrder(Order order){
        return orderRepository.findById(order.getId());
    }
    public Optional<Order> getOrder(int id){
        return orderRepository.findById(id);
    }

//    public Order newOrder(LocalDateTime date, String deliveryAddress, User user){
//        Order r = new Order(date, deliveryAddress, user);
//        return orderRepository.save(r);
//    }

    public Order newOrder(LocalDateTime date, String deliveryAddress, String username, Cart cart, ShippingType shippingType){
        int fullPrice = cart.getAmount() + shippingType.getPrice();

        Order order = new Order(date, deliveryAddress, userService.getUserByUsername(username), shippingType, fullPrice);
        orderRepository.save(order);
        Map<Integer, Integer> konvyIdDb = new HashMap<>();
        for(int i = 0; i<cart.getCartContent().size(); i++){
            int db = 1;
            for(int j = 0; j<cart.getCartContent().size(); j++){
                if(cart.getCartContent().get(i).getId() == cart.getCartContent().get(j).getId() && i != j){
                    db++;
                }
            }
            if(!konvyIdDb.containsKey(cart.getCartContent().get(i).getId())){
                konvyIdDb.put(cart.getCartContent().get(i).getId(), db);
            }
        }

        for (Map.Entry<Integer, Integer> entry : konvyIdDb.entrySet()) {
            orderedBookService.newOrderedBook(getOrder(order).get(),
                    bookService.getBookById(entry.getKey()).get(),
                    entry.getValue());
        }
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            list.add(order);
        }
        return list;
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

}
