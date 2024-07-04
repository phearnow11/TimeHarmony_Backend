package com.example.TimeHarmony.dtos;

import lombok.Builder;

public abstract class Payment {
    @Builder
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
