package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Cart;

public interface ICartService {
    Cart addToCart(String watch_id, String member_id);

    List<Cart> getAllCart(String member_id);

    String deleteCart(String cart_id);

    String deleteMutipleCart(List<String> cart_id);

    String saveChecked(Map<String, Integer> cartid);

    List<Cart> getCartsbyId(List<String> ids);

    String updateCartsOrder(List<Cart> carts, String order_id);

}
