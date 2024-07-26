package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.dtos.PaymentDTO;
import com.example.TimeHarmony.dtos.ResponseObject;
import com.example.TimeHarmony.entity.Payment;
import com.example.TimeHarmony.service.CartService;
import com.example.TimeHarmony.service.PaymentService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.WatchService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService PAYMENT_SERVICE;

    @Autowired
    private WatchService WATCH_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private CartService CART_SERVICE;

    @RequestMapping(value = "/vn-pay", method = RequestMethod.POST)
    public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest request,
            @RequestBody List<String> data) {
        try {
            byte PAYMENT_PROCESSING = 5;
            for (int state : WATCH_SERVICE.getWatchState(data))
                if (state != 1) {
                    throw new Exception("An Error occur");
                }
            WATCH_SERVICE.updateWatchesState(data, PAYMENT_PROCESSING);
            CART_SERVICE.updateAllByIDS(data, 0);
            return new ResponseObject<>(HttpStatus.OK, "Success", PAYMENT_SERVICE.createVnPayPayment(request));
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.LOCKED, e.toString(), null);
        }
    }

    @RequestMapping(value = "/insert-payment-detail", method = RequestMethod.POST)
    public Payment savePayment(@RequestBody Map<String, String> data) {
        try {
            if (!Boolean.parseBoolean(data.get("isSuccess")))
                throw new Exception("Payment Failed");
            return PAYMENT_SERVICE.savePaymentDetail(data);
        } catch (Exception e) {
            byte VIEW_STATE = 1;
            WATCH_SERVICE.updateWatchesState(STRING_SERVICE.jsonArrToStringList(data.get("wids")), VIEW_STATE);
            CART_SERVICE.updateAllByIDS(STRING_SERVICE.jsonArrToStringList(data.get("wids")), VIEW_STATE);
            return null;
        }
    }

}
