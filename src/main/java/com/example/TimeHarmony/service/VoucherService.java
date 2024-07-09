package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Vouchers;
import com.example.TimeHarmony.repository.VoucherRepository;
import com.example.TimeHarmony.service.interfacepack.IVoucherService;

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepository VOUCHER_REPOSITORY;

    @Override
    public Vouchers addVoucher(Vouchers v) {
        return VOUCHER_REPOSITORY.save(v);
    }

    @Override
    public String permaDeleteVoucher(String vid) {
        try {
            VOUCHER_REPOSITORY.deleteById(vid);
            return "Voucher deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String removeVoucherAmount(int num, String vid) {
        try {
            VOUCHER_REPOSITORY.removeNumberOfVoucher(num, vid);
            return "Voucher " + vid + " delete " + num;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<Vouchers> getVouchers() {
        return VOUCHER_REPOSITORY.findAll();
    }

    @Override
    public List<Vouchers> getVouchersNotExpired() {
        return VOUCHER_REPOSITORY.getVouchersNotExpired();
    }

}
