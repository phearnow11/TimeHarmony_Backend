package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.OrderLocation;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.enumf.OrderState;

public interface IOrderService {

    String makeOrder(List<String> wids, String m_id, String notice, float total_price, Addresses addr, String tno);

    String updateCartOrder(List<String> wids, String cid, String oid);

    String deleteOrder(String oid);

    List<Orders> getOrderFromMember(String mid);

    List<String> getWatchInOrder(String oid);

    Map<String, Object> getOrderDetail(String oid);

    List<Orders> getOrders();

    OrderState getOrderState(String oid);

    String cancelOrder(String oid);

    String confirmOrder(String oid);

    List<Orders> getPendingOrder();

    List<OrderLocation> getOrderLocations(String oid);

    String getPaymentMethod(String oid);
}
