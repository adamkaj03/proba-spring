package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Book;
import com.adam.buzas.onlab.main.model.Cart;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CartService {

    /**
     * Végig megy a kosár tartalmán és megszámolja milyen értékű
     * @param cart ezt a kosarat számolja össze
     * @return Visszadja a vásárlás összegét
     */
    private int cartAmountSum(Cart cart){
        int newAmount = 0;
        for(Book k: cart.getCartContent()){
            newAmount += k.getPrice();
        }
        return newAmount;
    }


    public void newElementIntoCart(Book book, Cart cart){
        cart.getCartContent().add(book);
        cart.setAmount(cartAmountSum(cart));
    }

    public void elementDeleteFromCart(Book k, Cart cart){
        Iterator<Book> iterator = cart.getCartContent().iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (k.getId() == book.getId()) {
                iterator.remove();
                break;
            }
        }
        cart.setAmount(cartAmountSum(cart));
    }

}
