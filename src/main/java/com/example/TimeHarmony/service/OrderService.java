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
import com.example.TimeHarmony.enumf.OrderState;
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

    @Autowired
    private PaymentService PAYMENT_SERVICE;

    @Autowired
    private WatchService WATCH_SERVICE;

    @Override
    public String makeOrder(List<String> wids, String m_id, String notice, float total_price, Addresses addr,
            String tno) {
        try {
            String order_id = "O" + STRING_SERVICE.autoGenerateString(11);
            Members m = MEMBER_SERVICE.getMemberbyID(m_id).get();

            Orders order = new Orders(order_id, m, Timestamp.valueOf(LocalDateTime.now()), addr.getAddress_detail(),
                    addr.getName(), addr.getPhone(), notice, total_price, null, OrderState.PENDING);
            ORDER_REPOSITORY.save(order);
            updateCartOrder(wids, m.getCart_id(), order_id);

            PAYMENT_SERVICE.updateOrderId(order_id, tno);

            byte WAITING_STATE = 3;
            WATCH_SERVICE.updateWatchesState(wids, WAITING_STATE);

            ORDER_REPOSITORY.deleteWatchInCartWhenConfirm(order_id);

            return order_id;
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
    public List<Orders> getOrders() {
        return ORDER_REPOSITORY.findAll();
    }

    @Override
    public OrderState getOrderState(String oid) {
        return ORDER_REPOSITORY.getState(oid);
    }

    @Override
    public String cancelOrder(String oid) {
        try {
            WATCH_REPOSITORY.cancelOrder(oid);
            ORDER_REPOSITORY.deleteOrder(oid);
            ORDER_REPOSITORY.updateOrderState(OrderState.DELETED.getSTATE_VALUE(), oid);
            return "Order " + oid + " deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String confirmOrder(String oid) {
        try {
            if (ORDER_REPOSITORY.getState(oid) != OrderState.SHIPPING)
                throw new Exception("Order is not Shipping");
            WATCH_REPOSITORY.orderSucess(oid);
            ORDER_REPOSITORY.updateOrderState(OrderState.SUCCESS.getSTATE_VALUE(), oid);
            return "Order is confirmed success";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<Orders> getPendingOrder() {
        return ORDER_REPOSITORY.getOrderByState(OrderState.PENDING.getSTATE_VALUE());
    }

}
