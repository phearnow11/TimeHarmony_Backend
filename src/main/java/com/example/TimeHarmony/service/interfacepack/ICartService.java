package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Cart;
import com.example.TimeHarmony.entity.Watch;
import com.nimbusds.jose.shaded.gson.JsonElement;

public interface ICartService {
    String addToCart(String watch_id, String member_id);

    List<Watch> getAllWatchFromCart(String member_id);

    String deleteCart(String cart_id);

    String deleteMutipleCart(List<String> cart_id);

    String saveChecked(JsonElement data);

    List<Cart> getCartsbyId(List<String> ids);

    String updateCartsOrder(List<Cart> carts, String order_id);

    Cart addNewCart();

}
