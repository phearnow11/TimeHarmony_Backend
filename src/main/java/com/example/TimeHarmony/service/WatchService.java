package com.example.TimeHarmony.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.Filter;
import com.example.TimeHarmony.dtos.WatchImages;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IWatchService;

@Service
public class WatchService implements IWatchService {

  @Autowired
  private WatchRepository WATCH_REPOSITORY;

  @Autowired
  private SellerService SELLER_REPOSITORY;

  @Override
  public String generateWatchId() {
    int leftLimit = 48; // '0'
    int rightLimit = 57; // '9'
    int targetStringLength = 11;

    Random random = new Random();

    String randomNumberString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return "W" + randomNumberString;
  }

  @Override
  public List<Watch> getWatchByGender(String gender) {
    return WATCH_REPOSITORY.findWatchesByGender(gender);
  }

  @Override
  public List<Watch> getWatchBySeries(String series) {
    return WATCH_REPOSITORY.findWatchesBySeries(series);
  }

  @Override
  public List<Watch> getWatchByBrand(String brand) {
    return WATCH_REPOSITORY.findWatchesByBrand(brand);
  }

  @Override
  public List<Watch> getWatchByStyle(String style) {
    return WATCH_REPOSITORY.findWatchesByStyle(style);
  }

  @Override
  public List<Watch> getListWatchDECS() {
    return WATCH_REPOSITORY.findAll(Sort.by(Sort.Direction.DESC, "watch_id"));
  }

  @Override
  public List<Watch> getWatchByFeatures(String features) {

    return WATCH_REPOSITORY.findWatchesByFeatures(features);
  }

  @Override
  public List<Watch> get30WatchesSortedByCreationDate() {
    return WATCH_REPOSITORY.find30watchesByDESCDate();
  }

  @Override
  public List<Watch> loadMoreWatchesSortedByCreationDate(List<Watch> old_watch_list) {

    List<Watch> newWatches = WATCH_REPOSITORY.findNext18WatchesbyDESCDate(old_watch_list.size() - 1);

    old_watch_list.addAll(newWatches);
    return old_watch_list;
  }

  @Override
  public List<Watch> nextPage(int numpage) {

    return WATCH_REPOSITORY.findNextPageDESCDate(numpage * 60 - 1);
  }

  @Override
  public List<Watch> getPage01() {
    return WATCH_REPOSITORY.get1pageOfWatchByDESCDate();
  }

  @Override
  public Watch getWatchById(String id) {
    if (id.isEmpty())
      return null;
    Optional<Watch> watch = Optional.empty();
    watch = WATCH_REPOSITORY.findById(id);
    if (watch.isPresent())
      return watch.get();
    return null;
  }

  @Override
  public List<Watch> getWatchesByFilter(Filter filter) {

    // chưa cần làm

    throw new UnsupportedOperationException("Unimplemented method 'getWatchesByFilter'");
  }

  @Override
  public List<Watch> getWatchesByPriceRange(float leftlimit, float rightlimit) {
    return WATCH_REPOSITORY.findWatchByRangePrice(leftlimit, rightlimit);
  }

  @Override
  public List<Watch> getWatchesByDialColor(String color) {
    return WATCH_REPOSITORY.findWatchesByDialColor(color);
  }

  @Override
  public List<Watch> getWatchesByCaseShape(String case_shape) {
    return WATCH_REPOSITORY.findWatchesByCaseShape(case_shape);
  }

  @Override
  public List<Watch> getWatchesByBandType(String band) {
    return WATCH_REPOSITORY.findWatchesByBandType(band);
  }

  @Override
  public List<Watch> getWatchesByMovement(String movement) {
    return WATCH_REPOSITORY.findWatchesByMovement(movement);
  }

  @Override
  public String updateImages(String id, List<String> urls) {
    try {
      Watch w = getWatchById(id);
      List<String> old_url = w.getImage_url();
      for (String i : old_url)
        WATCH_REPOSITORY.deleteImage(id, i);
      for (String new_url : urls)
        WATCH_REPOSITORY.saveWatch_Images(id, new_url);
      return "Watch images save success!";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Watch> getWatchesFromWatchID(List<String> ids) {
    try {
      return WATCH_REPOSITORY.findAllById(ids);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  @Override
  public String deleteWatch(String id, String sid) {
    try {
      int state = WATCH_REPOSITORY.getState(id);
      if (!(state == 1 || state == 0))
        throw new Exception("Can not delete");
      WATCH_REPOSITORY.deleteWatch(id, UUID.fromString(sid));
      return "Watch deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String deleteWatches(List<String> wids, String sid) {
    try {
      byte STATE = 2;
      Sellers s = SELLER_REPOSITORY.getSellerbyId(sid);
      List<Watch> w_owned = s.getWatches();
      List<Watch> w_chosen = getWatchByIds(wids);
      for (Watch w : w_chosen) {
        if (w_owned.contains(w))
          WATCH_REPOSITORY.updateWatchState(STATE, w.getWatch_id());
      }
      return "Watches deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Watch> getWatchesbyState() {
    try {
      byte STATE = 0;
      return WATCH_REPOSITORY.findAllByState(STATE);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String deleteImages(String id, List<String> urls) {
    try {
      for (String url : urls) {
        WATCH_REPOSITORY.deleteImage(id, url);
      }
      return "Watch images deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Watch> getWatchByIds(List<String> ids) {
    try {
      return WATCH_REPOSITORY.findAllById(ids);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  @Override
  public List<Watch> searchByKeyWord(String keyword) {
    List<Watch> result1 = WATCH_REPOSITORY.findByKeyWord(keyword);
    List<Watch> result2 = WATCH_REPOSITORY.findByKeyIfFullTextNull(keyword);
    if (result1.size() != 0) {
      return result1;
    } else {
      return result2;
    }
  }

  @Override
  public Map<String, Object> getWatchNum() {

    Map<String, Object> res = new HashMap<>();
    try {
      res.put("watch_num", WATCH_REPOSITORY.getWatchNum());
      return res;
    } catch (Exception e) {
      res.put("error", e);
      return res;
    }
  }

  @Override
  public Map<String, Object> getWatchByFilter(Map<String, Object> data) {
    Map<String, Object> res = new HashMap<>();
    try {
      List<Watch> watches = WATCH_REPOSITORY.getWatchesByFilter(
          data.get("keyword") != null ? data.get("keyword").toString() : null,
          data.get("gender") != null ? data.get("gender").toString() : null,
          data.get("series") != null ? data.get("series").toString() : null,
          data.get("brand") != null ? data.get("brand").toString() : null,
          data.get("style") != null ? data.get("style").toString() : null,
          data.get("feature") != null ? data.get("feature").toString() : null,
          data.get("lprice") != null ? Float.valueOf(data.get("lprice").toString()) : 0,
          data.get("hprice") != null ? Float.valueOf(data.get("hprice").toString()) : Float.MAX_VALUE,
          PageRequest.of(Integer.valueOf(data.get("page").toString()), 60));
      res.put("watches", watches);
      res.put("watch_num", WATCH_REPOSITORY.getWatchNumWithConditions(
          data.get("gender") != null ? data.get("gender").toString() : null,
          data.get("series") != null ? data.get("series").toString() : null,
          data.get("brand") != null ? data.get("brand").toString() : null,
          data.get("style") != null ? data.get("style").toString() : null,
          data.get("feature") != null ? data.get("feature").toString() : null,
          data.get("lprice") != null ? Float.valueOf(data.get("lprice").toString()) : 0,
          data.get("hprice") != null ? Float.valueOf(data.get("hprice").toString()) : Float.MAX_VALUE));
      return res;
    } catch (Exception e) {
      System.out.println(e);
      return res;
    }
  }

  @Override
  public List<WatchImages> getWatchImages(String wid) {
    try {
      return WATCH_REPOSITORY.getWatchImages(wid);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  @Override
  public String updateWatchesState(List<String> ids, byte state) {
    try {
      for (String id : ids) {
        WATCH_REPOSITORY.updateWatchState(state, id);
      }
      return "Watches'state updated successfully";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Integer> getWatchState(List<String> ids) {
    List<Integer> res = new ArrayList<>();
    for (String id : ids) {
      res.add(WATCH_REPOSITORY.getState(id));
    }
    return res;
  }

  @Override
  public Watch updateWatch(Map<String, String> data, Watch existingWatch) {

    try {
      if (existingWatch.getState() != 0)
        throw new Exception("Logic error");
      if (data.get("description") != null) {
        existingWatch.setWatch_description(data.get("description"));
      }
      if (data.get("name") != null) {
        existingWatch.setWatch_name(data.get("name"));
      }
      if (data.get("price") != null) {
        float price = Float.parseFloat(data.get("price"));
        if (price < 1000 || price > 1000000000)
          throw new Exception("Over Price");
        existingWatch.setPrice(price);
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

      return WATCH_REPOSITORY.save(existingWatch);
    } catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
  }

}
