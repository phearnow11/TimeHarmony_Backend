package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.dtos.WatchInCart;

public interface ICartService {
    String insertToCart(String cid, String wid, String mid);

    boolean checkWatchInCart(String cid, String wid);

    boolean checkMyWatch(String mid, String wid);

    List<WatchInCart> getCart(String cid);

    String delete1watchInCart(String cid, String wid);

    String deleteCart(String cid, List<String> wid);

    List<String> getWatchInCart(String cid);

    void updateCartWhenCompleteOrder(String cid, String wid);
}
