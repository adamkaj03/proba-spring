package com.adam.buzas.onlab.main.restcontrollers;

import com.adam.buzas.onlab.main.model.Cart;
import com.adam.buzas.onlab.main.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/kosar")
    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("kosar");
        for(int i = 0; i<cart.getCartContent().size(); i++){
            System.out.println(cart.getCartContent().get(i));
        }
        return cart;
    }

    @PutMapping("/kosar")
    public Cart updateCart(@RequestBody Cart cart, HttpSession session) {
        session.setAttribute("kosar", cart);
        for(int i = 0; i<cart.getCartContent().size(); i++){
            System.out.println(cart.getCartContent().get(i));
        }
        return cart;
    }

    @PostMapping("/kosar")
    public Cart postCart(@RequestBody Cart cart, HttpSession session) {
        session.setAttribute("kosar", cart);
        System.out.println(cart.getCartContent().get(0).toString());
        return cart;
    }

    @DeleteMapping("/kosar")
    public void deleteCart(HttpSession session){
        session.setAttribute("kosar", new Cart());
    }
}
