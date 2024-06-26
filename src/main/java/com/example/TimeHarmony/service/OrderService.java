package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.repository.OrderRepository;
import com.example.TimeHarmony.service.interfacepack.IOrderService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository ORDER_REPOSITORY;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Override
    public String makeOrder(List<String> wids, String m_id, String notice, long total_price, Addresses addr) {
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
}
