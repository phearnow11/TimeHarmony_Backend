package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String makeOrder(List<String> cids, String m_id) {
        String order_id = "O" + STRING_SERVICE.autoGenerateString(11);
        try {
            Members m = MEMBER_SERVICE.getMemberbyID(m_id).get();
            Timestamp time = Timestamp.valueOf(LocalDateTime.now());
            return "Order" + order_id + " created";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
