package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Book;
import com.adam.buzas.onlab.main.model.Order;
import com.adam.buzas.onlab.main.model.OrderedBook;
import com.adam.buzas.onlab.main.repository.OrderedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderedBookService {

    @Autowired
    private OrderedBookRepository orderedBookRepository;

    public Optional<OrderedBook> getOrderedBook(int id){
        return orderedBookRepository.findById(id);
    }

    public void newOrderedBook(Order order, Book book, int count){
        OrderedBook orderedBook = new OrderedBook(order, book, count);
        orderedBookRepository.save(orderedBook);
    }
}
