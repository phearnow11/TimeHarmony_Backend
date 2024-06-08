package com.example.TimeHarmony.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.SellerRepository;
import com.example.TimeHarmony.repository.UsersRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.ISellerService;

@Service
public class SellerService implements ISellerService {

    private final PasswordEncoder passwordEncoder;

    public SellerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private WatchRepository WATCH_REPOSITORY;
    @Autowired
    private UsersRepository USER_REPOSITORY;
    @Autowired
    private SellerRepository SELLER_REPOSITORY;
    @Autowired
    private AuthoritiesRepository AUTHORITIES_REPOSITORY;

    @Override
    public Watch createWatch(Watch watch, String seller_id) {
        Sellers s = SELLER_REPOSITORY.findById(UUID.fromString(seller_id)).get();
        watch.setSeller(s);
        return WATCH_REPOSITORY.save(watch);
    }

    @Override
    public String deleteWatchById(String watch_id) {

        return watch_id + " Deleted";
    }

   

    @Override
    public Sellers saveSeller(Sellers seller, Users logInfo) {
        logInfo.setPassword(passwordEncoder.encode(logInfo.getPassword()));
        USER_REPOSITORY.save(logInfo);
        AUTHORITIES_REPOSITORY.save(new Authorities(logInfo.getUsername(), Roles.ROLE_SELLER.name()));
        return SELLER_REPOSITORY.save(seller);
    }

    @Override
    public List<Watch> findAllWatchBySeller(Sellers s) {
        return s.getWatches();
    }

    @Override
    public Watch updateWatchByFields( Map<String, Object> data, String watch_id) {
        Optional<Watch> existingWatch = WATCH_REPOSITORY.findById(watch_id);

        if (existingWatch.isPresent()) {
            data.forEach((key, value)-> {
                Field field = ReflectionUtils.findField(Watch.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingWatch.get(), value);
            });
            return WATCH_REPOSITORY.save(existingWatch.get()); 
        }
        return null ; 
    }

}
