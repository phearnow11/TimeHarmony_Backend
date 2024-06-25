package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Addresses;

public interface IOrderService {

    String makeOrder(List<String> wids, String m_id, String notice, long total_price, Addresses addr);

    String updateCartOrder(List<String> wids, String cid, String oid);

}
