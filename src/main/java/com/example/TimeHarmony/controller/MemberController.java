package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.dtos.AccessHistory;
import com.example.TimeHarmony.dtos.Favorites;
import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Vouchers;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.CartService;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.OrderService;
import com.example.TimeHarmony.service.ReportService;
import com.example.TimeHarmony.service.SellerService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.VoucherService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private CartService CART_SERVICE;

    @Autowired
    private WatchService WATCH_SERVICE;

    @Autowired
    private OrderService ORDER_SERVICE;

    @Autowired
    private VoucherService VOUCHER_SERVICE;

    @Autowired
    private SellerService SELLER_SERVICE;

    @Autowired
    private ReportService REPORT_SERVICE;

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Optional<Members> getMember(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "getbyemail/{email}", method = RequestMethod.GET)
    public Members getMemberByEmail(@PathVariable("email") String email) {
        return MEMBER_SERVICE.getMemberbyEmail(email);
    }

    @RequestMapping(value = "get/addresses/{id}", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "update/email/{id}", method = RequestMethod.PUT)
    public String updateEmail(@PathVariable("id") String member_id,
            @RequestParam("email") String new_email) {

        return MEMBER_SERVICE.updateEmail(member_id, new_email);
    }

    @RequestMapping(value = "update/history/{id}", method = RequestMethod.POST)
    public String updateHistories(@PathVariable("id") String id, @RequestBody Map<String, List<String>> data) {
        List<String> urls = data.get("url");
        List<Timestamp> times = new ArrayList<>();
        for (String i : data.get("access_time")) {
            times.add(Timestamp.valueOf(i));
        }
        return MEMBER_SERVICE.updateAccessHistories(id, urls, times);
    }

    @RequestMapping(value = "get/history/{id}", method = RequestMethod.GET)
    public List<Map<String, String>> getAccessHistories(@PathVariable("id") String m_id) {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> resdata = new Hashtable<>();
        for (AccessHistory i : MEMBER_SERVICE.getAllAccessHistories(m_id)) {
            resdata.put("url", i.getUrl());
            resdata.put("access_time", i.getAccess_time().toString());
            res.add(new Hashtable<>(resdata));
            resdata.clear();
        }
        return res;
    }

    @RequestMapping(value = "save/address/{id}", method = RequestMethod.POST)
    public Addresses saveAddresses(@RequestBody Map<String, String> data, @PathVariable("id") String member_id) {
        Members cur_m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        Addresses nAdd = new Addresses(null, cur_m, data.get("name"),
                data.get("phone"), data.get("detail"), Boolean.valueOf(data.get("default")));
        return MEMBER_SERVICE.addAddress(nAdd);
    }

    @RequestMapping(value = "delete/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable("id") String id, @RequestParam("a_id") String address_id) {
        return MEMBER_SERVICE.deleteAddress(id, address_id);
    }

    @RequestMapping(value = "get/address/{id}", method = RequestMethod.GET)
    public List<Addresses> getAddress(@PathVariable("id") String id) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return MEMBER_SERVICE.getAddresses(id);
    }

    @RequestMapping(value = "add/to-cart/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable("id") String member_id, @RequestParam("watch_id") String watch_id) {
        Members m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        return CART_SERVICE.insertToCart(m.getCart_id(), watch_id, member_id);
    }

    @RequestMapping(value = "get/carts/{id}", method = RequestMethod.GET)
    public Map<String, Object> getCarts(@PathVariable("id") String member_id) {
        Map<String, Object> res = new Hashtable<>();
        Members m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        List<WatchInCart> cartInfo = CART_SERVICE.getCart(m.getCart_id());
        res.put("cart_id", m.getCart_id());
        res.put("cart_info", cartInfo);
        return res;
    }

    @RequestMapping(value = "delete/carts/{id}", method = RequestMethod.DELETE)
    public String deleteWatchCart(@PathVariable("id") String member_id, @RequestBody Map<String, List<String>> data) {
        Members m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        return CART_SERVICE.deleteCart(m.getCart_id(), data.get("wids"));
    }

    @RequestMapping(value = "to-seller", method = RequestMethod.POST)
    public String saveSeller(@RequestParam("id") String id, @RequestParam("username") String username) {

        return MEMBER_SERVICE.toSeller(id, username);
    }

    @RequestMapping(value = "add/favorites/{id}-{wid}", method = RequestMethod.POST)
    public String addFavories(@PathVariable("id") String member_id, @PathVariable("wid") String wid) {
        return MEMBER_SERVICE.addFavorites(member_id, wid);
    }

    @RequestMapping(value = "get/favorites/{id}", method = RequestMethod.GET)
    public List<Watch> getFavorites(@PathVariable("id") String member_id) {

        List<Favorites> favorites = MEMBER_SERVICE.getFavoritesFromMember(member_id);
        List<String> wids = new ArrayList<>();
        favorites.forEach(f -> wids.add(f.getWatch_id()));
        return WATCH_SERVICE.getWatchByIds(wids);
    }

    @RequestMapping(value = "delete/favorites/{id}", method = RequestMethod.DELETE)
    public String deleteFavorites(@PathVariable("id") String member_id, @RequestParam("wid") String wid) {
        return MEMBER_SERVICE.deleteFavorites(member_id, wid);
    }

    @RequestMapping(value = "add/order/{id}", method = RequestMethod.POST)
    public String makeOrder(@PathVariable("id") String member_id, @RequestBody Map<String, Object> data) {

        List<String> wids = STRING_SERVICE.jsonArrToStringList(data.get("wids"));
        Addresses addr = MEMBER_SERVICE.getAddressByAddressId(data.get("address").toString());

        return ORDER_SERVICE.makeOrder(wids, member_id, data.get("notice").toString(),
                Float.parseFloat(data.get("total_price").toString()), addr, data.get("transaction_no").toString());
    }

    @RequestMapping(value = "cancel/order/{oid}", method = RequestMethod.PUT)
    public String cancelOrder(@PathVariable("oid") String oid) {
        return ORDER_SERVICE.cancelOrder(oid);
    }

    @RequestMapping(value = "get/order/{id}", method = RequestMethod.GET)
    public Map<String, Object> getOrders(@PathVariable("id") String mid) {
        Map<String, Object> res = new Hashtable<>();
        res.put("orders", ORDER_SERVICE.getOrderFromMember(mid));
        return res;
    }

    // localhost:8080/member/get/order/watch/{oid}
    @RequestMapping(value = "get/order/detail/{oid}", method = RequestMethod.GET)
    public Map<String, Object> getWatchInOrder(@PathVariable("oid") String oid) {
        return ORDER_SERVICE.getOrderDetail(oid);
    }

    @RequestMapping(value = "get/order/state/{oid}", method = RequestMethod.GET)
    public String getOrderStatus(@PathVariable("oid") String oid) {
        return ORDER_SERVICE.getOrderState(oid).name();
    }

    @RequestMapping(value = "update/user/image/{id}", method = RequestMethod.PATCH)
    public String updateMemberImage(@PathVariable("id") String member_id, @RequestParam("url") String url) {
        return MEMBER_SERVICE.updateMemberImage(member_id, url);
    }

    @RequestMapping(value = "update/user/password/{username}", method = RequestMethod.PATCH)
    public String updateMemberPassword(@PathVariable("username") String username,
            @RequestParam("npwd") String new_pwd) {
        return MEMBER_SERVICE.changeUserPassword(username, new_pwd);
    }

    @GetMapping("/test")
    public String test(@RequestBody Map<String, Object> data, @RequestParam() Map<String, Object> a) {

        return "" + (Boolean) a.get("test");
    }

    @RequestMapping(value = "get/voucher/all", method = RequestMethod.GET)
    public List<Vouchers> getVouchers() {
        return VOUCHER_SERVICE.getVouchersNotExpired();
    }

    @RequestMapping(value = "check/password/{username}", method = RequestMethod.GET)
    public String checkPassword(@PathVariable("username") String username,
            @RequestParam("pwd") String pwd) {
        return MEMBER_SERVICE.checkPassword(username, pwd);
    }

    @RequestMapping(value = "update/rate/{id}", method = RequestMethod.PUT)
    public String updateRate(@RequestParam("rate") float rate, @PathVariable("id") String id,
            @RequestParam("rater") String rater) {
        if (id.equals(rater))
            return "Can not rate yourself";
        return SELLER_SERVICE.setRate(rate, id, rater);
    }

    @RequestMapping(value = "get/rate/{id}", method = RequestMethod.GET)
    public float getRate(@PathVariable("id") String id) {
        return SELLER_SERVICE.getRate(id);
    }

    @RequestMapping(value = "send/order-report", method = RequestMethod.POST)
    public Report createOrderReport(@RequestBody Map<String, String> data) {
        return REPORT_SERVICE.createReport(data);
    }

    @RequestMapping(value = "confirm-success/order/{oid}", method = RequestMethod.POST)
    public String orderSuccess(@PathVariable("oid") String oid) {
        return ORDER_SERVICE.confirmOrder(oid);
    }

    @RequestMapping(value = "update/member/{id}", method = RequestMethod.PUT)
    public String updateMember(@PathVariable("id") String id, @RequestBody Map<String, Object> data) {
        return MEMBER_SERVICE.updateMember(id, data);
    }
}
