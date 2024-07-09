package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Vouchers;

public interface IVoucherService {

    Vouchers addVoucher(Vouchers v);

    String permaDeleteVoucher(String vid);

    String removeVoucherAmount(int num, String vid);

    List<Vouchers> getVouchers();
}
