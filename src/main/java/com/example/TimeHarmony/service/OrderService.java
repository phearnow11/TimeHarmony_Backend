package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.repository.OrderRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IOrderService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository ORDER_REPOSITORY;

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Override
    public String makeOrder(List<String> wids, String m_id, String notice, float total_price, Addresses addr) {
        try {
            String order_id = "O" + STRING_SERVICE.autoGenerateString(11);
            Members m = MEMBER_SERVICE.getMemberbyID(m_id).get();
            Orders order = new Orders(order_id, m, Timestamp.valueOf(LocalDateTime.now()), addr.getAddress_detail(),
                    addr.getName(), addr.getPhone(), notice, total_price);
            ORDER_REPOSITORY.save(order);
            updateCartOrder(wids, m.getCart_id(), order_id);
            
            return "Order Created";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String updateCartOrder(List<String> wids, String cid, String oid) {
        try {
            for (String wid : wids)
                ORDER_REPOSITORY.updateOrderCart(oid, cid, wid);
            return "Cart Order relationship updated";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteOrder(String oid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public List<Orders> getOrderFromMember(String mid) {
        try {
            return ORDER_REPOSITORY.getOrdersByMemberId(UUID.fromString(mid));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<String> getWatchInOrder(String oid) {
        try {
            return ORDER_REPOSITORY.getWatchesInOrder(oid);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getOrderDetail(String oid) {
        try {

            Map<String, Object> res = new Hashtable<>();
            res.put("watch", ORDER_REPOSITORY.getWatchesInOrder(oid));
            res.put("order_detail", ORDER_REPOSITORY.findById(oid));
            return res;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public float total_price(List<String> wids) {
        float sum = 0; 
        try {
            for (String i : wids ) { 
                float w = WATCH_REPOSITORY.findById(i).get().getPrice(); 
                sum = sum + w ; 
            }
            return sum ; 
        } catch (Exception e) {
            return 0 ; 
        }
    }
}
