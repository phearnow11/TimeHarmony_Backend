package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Cart;
import com.example.TimeHarmony.repository.CartRepository;
import com.example.TimeHarmony.service.interfacepack.ICartService;

@Service
public class CartService implements ICartService {

    @Autowired
    private StringService STRING_SERVICE;
    @Autowired
    private CartRepository CART_REPOSITORY;

    @Override
    public Cart addToCart(String watch_id, String member_id) {
        Cart cart = new Cart(STRING_SERVICE.autoGenerateString(10), watch_id, UUID.fromString(member_id), "",
                Timestamp.valueOf(LocalDateTime.now()));
        return CART_REPOSITORY.save(cart);
    }

    @Override
    public List<Cart> getAllCart(String member_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCart'");
    }

    @Override
    public void deleteFromCart(String cart_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFromCart'");
    }

}
