package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Vouchers;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.StaffService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.VoucherService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService STAFF_SERVICE;
    @Autowired
    private WatchService WATCH_SERVICE;
    @Autowired
    private VoucherService VOUCHER_SERVICE;
    @Autowired
    private StringService STRING_SERVICE;

    @RequestMapping(value = "approve-watch", method = RequestMethod.PATCH)
    public String approveWatch(@RequestParam("watch_id") String watch_id) {
        return STAFF_SERVICE.approveWatch(watch_id);
    }

    @RequestMapping(value = "unapprove-watch", method = RequestMethod.PATCH)
    public String unApproveWatch(@RequestParam("watch_id") String watch_id) {
        return STAFF_SERVICE.unApproveWatch(watch_id);
    }

    @RequestMapping(value = "get/watch", method = RequestMethod.GET)
    public List<Map<String, String>> getWatches() {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> watch = new Hashtable<>();
        List<Watch> ads = WATCH_SERVICE.getWatchesbyState();
        for (Watch w : ads) {
            watch.put("id", w.getWatch_id());
            watch.put("name", w.getWatch_name());
            watch.put("seller_id", w.getSeller().getMember_id().toString());
            watch.put("seller_name", w.getSeller().getFirst_name() + w.getSeller().getLast_name());
            watch.put("image", w.getImage_url().isEmpty() ? null : w.getImage_url().get(0));
            watch.put("price", Float.toString(w.getPrice()));
            watch.put("upload_date", w.getWatch_create_date().toString());
            watch.put("status", Byte.toString(w.getState()));
            res.add(new Hashtable<>(watch));
        }
        return res;
    }

    @RequestMapping(value = "get/watch-state/{state}", method = RequestMethod.GET)
    public List<Watch> getWatchesByState0(@PathVariable("state") int state) {
        return STAFF_SERVICE.getAllStateWatch(state);
    }

    @RequestMapping(value = "create/voucher", method = RequestMethod.POST)
    public Vouchers createVoucher(@RequestBody Map<String, Object> data) {

        Vouchers v = new Vouchers(STRING_SERVICE.autoGenerateString(12), data.get("name").toString(),
                data.get("description").toString(), (Integer) data.get("value"),
                Float.parseFloat(data.get("value_percentage").toString()),
                (Integer) data.get("limit"), (Integer) data.get("condition"), Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now().plusHours(Long.parseLong(data.get("expired_date").toString()))),
                (Integer) data.get("quantity"));
        return VOUCHER_SERVICE.addVoucher(v);
    }

    @RequestMapping(value = "remove/amount/voucher", method = RequestMethod.PATCH)
    public String removeVoucherAmount(@RequestBody Map<String, Object> data) {
        return VOUCHER_SERVICE.removeVoucherAmount((Integer.parseInt(data.get("num").toString())),
                data.get("vid").toString());
    }

    @RequestMapping(value = "get/voucher/all", method = RequestMethod.GET)
    public List<Vouchers> getVouchers() {
        return VOUCHER_SERVICE.getVouchers();
    }

    @RequestMapping(value = "remove/perma/voucher", method = RequestMethod.DELETE)
    public String permaDeleteVoucher(@RequestParam("vid") String vid) {
        return VOUCHER_SERVICE.permaDeleteVoucher(vid);
    }
}
