package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
                Timestamp.valueOf(LocalDateTime.now()), 0);
        return CART_REPOSITORY.save(cart);
    }

    @Override
    public List<Cart> getAllCart(String member_id) {
        try {
            return CART_REPOSITORY.getCartFromMember(UUID.fromString(member_id));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String deleteCart(String cart_id) {
        try {
            CART_REPOSITORY.deleteById(cart_id);
            return "Cart " + cart_id + " deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteMutipleCart(List<String> cart_id) {
        try {
            CART_REPOSITORY.deleteAllById(cart_id);
            return "Carts deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String saveChecked(Map<String, Integer> cartid) {
        try {
            for (String ids : cartid.keySet()) {
                CART_REPOSITORY.updateCheck(cartid.get(ids), ids);
            }
            return "Cart check save";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<Cart> getCartsbyId(List<String> ids) {
        try {
            return CART_REPOSITORY.findAllById(ids);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String updateCartsOrder(List<Cart> carts, String order_id) {
        try {
            for (Cart i : carts) {
                CART_REPOSITORY.updateOrder(order_id, i.getCart_id());
            }
            return "Order id at Cart updated";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
