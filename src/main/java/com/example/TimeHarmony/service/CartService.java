package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.service.interfacepack.ICartService;

@Service
public class CartService implements ICartService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Override
    public String insertToCart(String cid, String wid) {
        if (checkWatchInCart(cid, wid)) {
        try {
            MEMBER_REPOSITORY.addToCart(wid, cid, null, 0, Timestamp.valueOf(LocalDateTime.now()), 1);
            return "Watch added to Cart";
        } catch (Exception e) {
            return e.toString();
        }
    }
        return "Watch aready in cart!" ; 
    }

    @Override
    public boolean checkWatchInCart(String cid, String wid) {
        String resTrue = "Watch existed in cart !"; 
        Optional<Members> m = MEMBER_REPOSITORY.getMemberByCartId(cid); 
        List<String> cartlist = m.get().getMyCarts() ; 
        for (String w : cartlist) {
            if ( w.equals(wid)){
                System.out.println("Watch existed in cart !");
                return false ; 
                
            }
        }
        return true ; 
    }


    @Override
    public List<WatchInCart> getCart(String cid) {
        try {
            return MEMBER_REPOSITORY.getCart(cid);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String deleteCart(String cid, String wid) {
        try {
            MEMBER_REPOSITORY.deleteWatchInCart(wid, cid);
            return "Watch Deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteCart(String cid, List<String> wids) {
        try {
            for (String wid : wids)
                MEMBER_REPOSITORY.deleteWatchInCart(cid, wid);
            return "Watches deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<String> getWatchInCart(String cid) {
        try {
            return MEMBER_REPOSITORY.getWatchesInCart(cid);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }



   
}
