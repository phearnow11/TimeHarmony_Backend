package com.example.TimeHarmony.service;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.interfacepack.ISellerService;

public class SellerService implements ISellerService {

    @Override
    public Watch createWatch(Watch watch) {

        // tạo xong rồi return lại Watch đã tạo

        throw new UnsupportedOperationException("Unimplemented method 'createWatch'");
    }

    @Override
    public String deleteWatchById(String watch_id) {

        return watch_id + " Deleted";
    }

    @Override
    public Watch updateWatch(Watch watch) {
        // update bên query đi
        return watch;
    }

}
