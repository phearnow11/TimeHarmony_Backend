package com.example.TimeHarmony.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.OrderRepository;
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
    @Autowired
    private OrderRepository ORDER_REPOSITORY;

    @Override
    public String createWatch(Watch watch, Sellers seller) {
        try {

            watch.setSeller(seller);
            WATCH_REPOSITORY.save(watch);
            return "Watch Created";
        } catch (Exception e) {
            return e.toString();
        }
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
    public List<Watch> findAllWatchBySeller(String sid) {
        int RESPONSE_LIMIT = 12;
        List<Watch> myWatches = WATCH_REPOSITORY.getWatchesBySeller(UUID.fromString(sid), Limit.of(RESPONSE_LIMIT));
        return myWatches;
    }

    @Override
    public Watch updateWatch(Watch newWatch, Watch existingWatch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWatch'");
    }

    @Override
    public Watch updateWatchByFields(Map<String, String> data, Watch existingWatch) {

        if (data.get("description") != null) {
            existingWatch.setWatch_description(data.get("description"));
        }
        if (data.get("name") != null) {
            existingWatch.setWatch_name(data.get("name"));
        }
        if (data.get("price") != null) {
            existingWatch.setPrice(Float.parseFloat(data.get("price")));
        }
        if (data.get("brand") != null) {
            existingWatch.setBrand(data.get("brand"));
        }
        if (data.get("series") != null) {
            existingWatch.setSeries(data.get("series"));
        }
        if (data.get("model") != null) {
            existingWatch.setModel(data.get("model"));
        }
        if (data.get("gender") != null) {
            existingWatch.setGender(data.get("gender"));
        }
        if (data.get("style") != null) {
            existingWatch.setStyle_type(data.get("style"));
        }
        if (data.get("subclass") != null) {
            existingWatch.setSub_class(data.get("subclass"));
        }
        if (data.get("madelabel") != null) {
            existingWatch.setMade_label(data.get("madelabel"));
        }
        if (data.get("calender") != null) {
            existingWatch.setCalender(data.get("calender"));
        }
        if (data.get("feature") != null) {
            existingWatch.setFeature(data.get("feature"));
        }
        if (data.get("movement") != null) {
            existingWatch.setMovement(data.get("movement"));
        }
        if (data.get("function") != null) {
            existingWatch.setFunctions(data.get("function"));
        }
        if (data.get("engine") != null) {
            existingWatch.setEngine(data.get("engine"));
        }
        if (data.get("waterresistant") != null) {
            existingWatch.setWater_resistant(data.get("waterresistant"));
        }
        if (data.get("bandcolor") != null) {
            existingWatch.setBand_color(data.get("bandcolor"));
        }
        if (data.get("bandtype") != null) {
            existingWatch.setBand_type(data.get("bandtype"));
        }
        if (data.get("clasp") != null) {
            existingWatch.setClasp(data.get("clasp"));
        }
        if (data.get("bracelet") != null) {
            existingWatch.setBracelet(data.get("bracelet"));
        }
        if (data.get("dialtype") != null) {
            existingWatch.setDial_type(data.get("dialtype"));
        }
        if (data.get("dialcolor") != null) {
            existingWatch.setDial_color(data.get("dialcolor"));
        }
        if (data.get("crystal") != null) {
            existingWatch.setCrystal(data.get("crystal"));
        }
        if (data.get("secondmaker") != null) {
            existingWatch.setSecond_makers(data.get("secondmaker"));
        }
        if (data.get("bezel") != null) {
            existingWatch.setBezel(data.get("bezel"));
        }
        if (data.get("bezelmaterial") != null) {
            existingWatch.setBezel(data.get("bezelmaterial"));
        }
        if (data.get("caseback") != null) {
            existingWatch.setCase_back(data.get("caseback"));
        }
        if (data.get("casedimension") != null) {
            existingWatch.setCase_dimension(data.get("casedimension"));
        }
        if (data.get("caseshape") != null) {
            existingWatch.setCase_shape(data.get("caseshape"));
        }

        return existingWatch;
    }

    @Override
    public Sellers getSellerbyId(String id) {
        try {
            return SELLER_REPOSITORY.findById(UUID.fromString(id)).get();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String confirmShipping(String wid, String oid) {
        try {
            byte SHIP = 4;
            WATCH_REPOSITORY.updateWatchState(SHIP, wid);
            return "Confirm shipping";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<Watch> getWaitingList(String mid) {
        return WATCH_REPOSITORY.getWaitingWatches(UUID.fromString(mid), Limit.of(20));
    }

    @Override
    public String confirmOrder(String oid) {
        try {
            WATCH_REPOSITORY.confirmOrder(oid);
            return "Order Shipping Confirm";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String setRate(float incoming_rate, String sid, String rater) {
        try {
            if (!SELLER_REPOSITORY.checkExistRater(UUID.fromString(rater)).isEmpty())
                throw new Exception("You have rated this seller!");
            SELLER_REPOSITORY.updateRate(UUID.fromString(sid), incoming_rate, UUID.fromString(rater));
            return "Rate Updated";
        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public Float getRate(String sid) {
        return SELLER_REPOSITORY.getRate(UUID.fromString(sid));
    }

    @Override
    public List<String[]> getOrderFromWatch(String wid) {
        List<String[]> res = new ArrayList<>();
        for (String oid : WATCH_REPOSITORY.getOrderFromWatch(wid)) {
            String[] orderInfo = { oid, ORDER_REPOSITORY.getCreationDate(oid) };
            res.add(orderInfo);
        }
        return res;
    }

    @Override
    public float getTotalAmountBySeller(String sid) {
        Sellers s = SELLER_REPOSITORY.findById(UUID.fromString(sid)).get();
        List<Watch> wlist = s.getWatches();
        float total = 0;
        for (int i = 1; i < wlist.size(); i++) {
            if (wlist.get(i).getState() == 7) {
                total = total + wlist.get(i).getPrice();
            }
        }

        return total;
    }

}
