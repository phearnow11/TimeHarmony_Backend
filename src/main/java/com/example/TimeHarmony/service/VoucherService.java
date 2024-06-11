package com.example.TimeHarmony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Voucher;
import com.example.TimeHarmony.repository.VoucherRepository;
import com.example.TimeHarmony.service.interfacepack.IVoucherService;

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepository VOUCHER_REPOSITORY;

    @Override
    public Voucher addVoucher(Voucher v) {
        return VOUCHER_REPOSITORY.save(v);
    }

}
