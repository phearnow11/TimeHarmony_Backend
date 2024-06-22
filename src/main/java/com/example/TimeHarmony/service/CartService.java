package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.entity.Cart;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.WatchCartState;
import com.example.TimeHarmony.repository.CartRepository;
import com.example.TimeHarmony.service.interfacepack.ICartService;
import com.nimbusds.jose.shaded.gson.JsonElement;

@Service
public class CartService implements ICartService {

    @Autowired
    private StringService STRING_SERVICE;
    @Autowired
    private CartRepository CART_REPOSITORY;
    @Autowired
    private MemberService MEMBER_SERVICE;
    @Autowired
    private WatchService WATCH_SERVICE;
    @Autowired
    private MapService MAP_SERVICE;

    @Override
    public List<Watch> getAllWatchFromCart(String member_id) {
        try {
            Cart c = MEMBER_SERVICE.getMemberbyID(member_id).get().getCart();
            List<Watch> rs = new ArrayList<>();
            for (WatchInCart i : CART_REPOSITORY.getWatchesInCart(c.getCart_id())) {
                rs.add(WATCH_SERVICE.getWatchById(i.getWatch_id()));
            }
            return rs;
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
    public String saveChecked(JsonElement data) {
        try {
            Map<String, Object> datamapped = MAP_SERVICE.convertJsonToObjectGson(data);
            System.out.println(datamapped);
            List<String> wids = STRING_SERVICE.jsonArrToStringList(datamapped.get("wids"));
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
            }
            return "Order id at Cart updated";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String addToCart(String watch_id, String member_id) {
        try {
            CART_REPOSITORY.addToCart(watch_id, MEMBER_SERVICE.getMemberbyID(member_id).get().getCart().getCart_id(), 0,
                    Timestamp.valueOf(LocalDateTime.now()), WatchCartState.NORMAL);
            return watch_id + " added to Cart";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Cart addNewCart() {
        try {
            String cid = "C" + STRING_SERVICE.autoGenerateString(11);
            Cart c = new Cart(cid, new ArrayList<>());
            return CART_REPOSITORY.save(c);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
