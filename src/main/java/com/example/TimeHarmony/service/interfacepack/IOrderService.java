package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Orders;

public interface IOrderService {

    String makeOrder(List<String> wids, String m_id, String notice, float total_price, Addresses addr);

    String updateCartOrder(List<String> wids, String cid, String oid);

    String deleteOrder(String oid);

    List<Orders> getOrderFromMember(String mid);

    List<String> getWatchInOrder(String oid);

    Map<String, Object> getOrderDetail(String oid);
}
