package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Cart;

public interface ICartService {
    Cart addToCart(String watch_id, String member_id);

    List<Cart> getAllCart(String member_id);

    void deleteFromCart(String cart_id);

}
