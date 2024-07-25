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
import com.example.TimeHarmony.service.PaymentService;
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

    @RequestMapping(value = "/vn-pay", method = RequestMethod.GET)
    public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest request,
            @RequestBody List<String> data) {
        try {
            byte PAYMENT_PROCESSING = 5;
            for (int state : WATCH_SERVICE.getWatchState(data))
                if (state != 2) {
                    throw new Exception("An Error occur");
                }
            // WATCH_SERVICE.updateWatchesState(new ArrayList<>(data), PAYMENT_PROCESSING);
            return new ResponseObject<>(HttpStatus.OK, "Success", PAYMENT_SERVICE.createVnPayPayment(request));
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.LOCKED, e.toString(), null);
        }
    }

    @RequestMapping(value = "/insert-payment-detail", method = RequestMethod.POST)
    public Payment savePayment(@RequestBody Map<String, String> data) {
        
        return PAYMENT_SERVICE.savePaymentDetail(data);
    }

}
