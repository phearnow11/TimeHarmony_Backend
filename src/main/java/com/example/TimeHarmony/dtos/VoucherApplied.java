package com.example.TimeHarmony.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoucherApplied {
    private String voucher_id;
    private String order_id;

    public VoucherApplied() {
    }
}
