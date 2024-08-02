package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.configuration.VNPAYConfig;
import com.example.TimeHarmony.dtos.PaymentDTO;
import com.example.TimeHarmony.entity.Payment;
import com.example.TimeHarmony.repository.PaymentRepository;
import com.example.TimeHarmony.util.VNPayUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

  @Autowired
  private PaymentRepository PAYMENT_REPOSITORY;

  private final VNPAYConfig vnPayConfig;

  public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request) {
    long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
    String bankCode = request.getParameter("bankCode");
    Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
    vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
    if (bankCode != null && !bankCode.isEmpty()) {
      vnpParamsMap.put("vnp_BankCode", bankCode);
    }
    vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
    // build query url
    String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
    String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
    String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
    queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
    String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
    return PaymentDTO.VNPayResponse.builder()
        .code("ok")
        .message("success")
        .paymentUrl(paymentUrl).build();
  }

  public String savePaymentDetail(Map<String, String> data) {
    try {

      Payment paymentdetail = new Payment();
      paymentdetail.setTransaction_no(data.get("transaction_no"));
      paymentdetail.setPayment_amount(Long.parseLong(data.get("payment_amount")) / 100);
      paymentdetail.setWeb_profit(Long.parseLong(data.get("payment_amount")) / 5100);
      paymentdetail.setBank_code(data.get("bank_code"));
      paymentdetail.setPayment_method(data.get("payment_method"));
      paymentdetail.setOrder_id(data.get("order_id"));
      paymentdetail.setMember_id(data.get("member_id"));
      paymentdetail.setCreate_at(new Timestamp(System.currentTimeMillis()));
      PAYMENT_REPOSITORY.save(paymentdetail);
      return "Payment Created Successfully";
    } catch (Exception e) {
      return e.toString();
    }

  }

  public String updateOrderId(String oid, String tno) {
    PAYMENT_REPOSITORY.updateOrderid(oid, tno);
    return "update successfully!";
  }

  public String getTransactionNoByOrderId(String oid) {// thinh
    return PAYMENT_REPOSITORY.getTransactionNoByOrderId(oid);
  }

  public String deleteTransaction(String id) {
    try {
      PAYMENT_REPOSITORY.deleteTransaction(id);
      return "Transaction " + id + "deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }
}
