package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.ICartService;

@Service
public class CartService implements ICartService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public String insertToCart(String cid, String wid, String mid) {
        if (!checkWatchInCart(cid, wid)) {
            if (!checkMyWatch(mid, wid)) {
                MEMBER_REPOSITORY.addToCart(wid, cid, null, 0, Timestamp.valueOf(LocalDateTime.now()), 1);
                return "Watch added to Cart";
            }
            return "Cannot buy your own watch!";
        }
        MEMBER_REPOSITORY.updateStateWatchInCart(1, cid, wid);
        return "Watch state changed!";
    }

    @Override
    public boolean checkWatchInCart(String cid, String wid) {
        List<String> wids = MEMBER_REPOSITORY.getAllWatchesInCart(cid);
        return wids.contains(wid);
    }

    public boolean checkMyWatch(String mid, String wid) {
        List<String> wids = WATCH_REPOSITORY.getWatchesBySeller(UUID.fromString(mid), Limit.unlimited()).stream()
                .map(w -> w.getWatch_id())
                .collect(Collectors.toList());
        System.out.println(wids);
        return wids.contains(wid);
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
    public String delete1watchInCart(String cid, String wid) {
        try {
            MEMBER_REPOSITORY.deletePermaWatchInCart(wid, cid);
            return "Watch Deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteCart(String cid, List<String> wids) {
        try {
            for (String wid : wids)
                MEMBER_REPOSITORY.updateStateWatchInCart(0, cid, wid);
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

    @Override
    public void updateCartWhenCompleteOrder(String cid, String wid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCartWhenCompleteOrder'");
    }

}
