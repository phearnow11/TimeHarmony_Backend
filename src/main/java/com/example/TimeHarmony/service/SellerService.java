package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.RequestStatus;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AppraiseRequestRepository;
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
  @Autowired
  private AppraiseRequestRepository APPRAISE_REQUEST_REPOSITORY;
  @Autowired
  private StringService STRING_SERVICE;

  @Override
  public String createWatch(Watch watch, Sellers seller) {
    try {

      watch.setSeller(seller);
      WATCH_REPOSITORY.save(watch);
      return watch.getWatch_id();
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
  public float getTotalProfitBySeller(String sid) {
    int WATCH_SUCCESS_STATE = 6;
    Sellers s = SELLER_REPOSITORY.findById(UUID.fromString(sid)).get();
    List<Watch> wlist = s.getWatches();
    float total = 0;
    for (int i = 0; i < wlist.size(); i++) {
      if (wlist.get(i).getState() == WATCH_SUCCESS_STATE) {
        total = total + wlist.get(i).getPrice();
      }
    }

    return total;
  }

  @Override
  public float getProfitByMonth(int month, int year, String sid) {
    List<Watch> wlist = WATCH_REPOSITORY.getWatchSoldByMonth(month, year, UUID.fromString(sid));
    System.out.println(wlist);
    float total = 0;
    /*
     * for (int i = 0; i < wlist.size(); i++) {
     * 
     * total = total + wlist.get(i).getPrice();
     * 
     * }
     */

    for (Watch i : wlist) {
      total = total + i.getPrice();
      System.out.println(i.getPrice());
    }
    return total;

  }

  @Override
  public int countSellWatch(String sid) {
    return WATCH_REPOSITORY.getSellerWatches(UUID.fromString(sid)).size();
  }

  @Override
  public int countSoldWatch(String sid) {
    return WATCH_REPOSITORY.getSellerSoldWatches(UUID.fromString(sid)).size();
  }

  @Override
  public float getProfitByDay(String date, String sid) {
    List<Watch> wlist = WATCH_REPOSITORY.getWatchSoldByDate(date, UUID.fromString(sid));
    float total = 0;
    for (int i = 0; i < wlist.size(); i++) {
      total = total + wlist.get(i).getPrice();
    }
    return total;
  }

  @Override
  public String createAppraiseRequest(String sid, Map<String, Object> data) {
    try {
      if (data.get("watch_id") == null)
        throw new Exception("Watch ID is required");
      if (APPRAISE_REQUEST_REPOSITORY.checkWatch(data.get("watch_id").toString()) != null)
        throw new Exception("Request of this watch is already created");
      if (data.get("appoinment_date") == null )
        throw new Exception("Appoinment Date is required"); 
      String request_id = "R" + STRING_SERVICE.autoGenerateString(11);
      AppraiseRequest request = new AppraiseRequest(request_id, UUID.fromString(sid), null,
          data.get("watch_id").toString(),
          Timestamp.valueOf(data.get("appoinment_date").toString()), 
          Timestamp.valueOf(LocalDateTime.now()),
          data.get("note") == null ? null : data.get("note").toString(), 
          RequestStatus.NEW);
      APPRAISE_REQUEST_REPOSITORY.save(request);

      return "Request created";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<AppraiseRequest> getMyRequest(String sid) {
    try {
      return APPRAISE_REQUEST_REPOSITORY.getRequestFromSeller(UUID.fromString(sid));
    } catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
  }

}
