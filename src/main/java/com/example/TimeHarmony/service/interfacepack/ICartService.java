package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.dtos.WatchInCart;

public interface ICartService {
    String insertToCart(String cid, String wid);

    boolean checkWatchInCart (String cid, String wid);  

    List<WatchInCart> getCart(String cid);

    String deleteCart(String cid, String wid);

    String deletedCart(String cid, List<String> wid);

    List<String> getWatchInCart(String cid);

}
