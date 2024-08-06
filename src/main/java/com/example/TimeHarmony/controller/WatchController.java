package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/watch")
@CrossOrigin
public class WatchController {

  @Autowired
  private WatchService WATCH_SERVICE;

  @RequestMapping(value = "get/gender", method = RequestMethod.GET)
  public List<Watch> getWatchByGender(@RequestParam("gender") String gender) {
    return WATCH_SERVICE.getWatchByGender(gender);
  }

  @RequestMapping(value = "get/series", method = RequestMethod.GET)
  public List<Watch> getWatchBySeries(@RequestParam("series") String series) {
    return WATCH_SERVICE.getWatchBySeries(series);
  }

  @RequestMapping(value = "get/brand", method = RequestMethod.GET)
  public List<Watch> getWatchByBrand(@RequestParam("brand") String brand) {
    return WATCH_SERVICE.getWatchByBrand(brand);
  }

  @RequestMapping(value = "get/style", method = RequestMethod.GET)
  public List<Watch> getWatchByStyle(@RequestParam("style") String style) {
    return WATCH_SERVICE.getWatchByStyle(style);
  }

  @RequestMapping(value = "filter", method = RequestMethod.GET)
  public Map<String, String> filterChain(@RequestParam Map<String, String> data) {
    return data;
  }

  @RequestMapping(value = "get/30-watches", method = RequestMethod.GET)
  public List<Watch> get30watchesbyDESCdate() {
    return WATCH_SERVICE.get30WatchesSortedByCreationDate();
  }

  @RequestMapping(value = "get/watch-page", method = RequestMethod.GET)
  public Map<String, Object> getNextPage(@RequestParam() Map<String, Object> data) {
    return WATCH_SERVICE.getWatchByFilter(data);
  }

  @RequestMapping(value = "get/watch-in-range-price", method = RequestMethod.GET)
  public List<Watch> getWatchInRange(@RequestParam("leftlimit") float leftlimit,
      @RequestParam("rightlimit") float rightlimit) {
    return WATCH_SERVICE.getWatchesByPriceRange(leftlimit, rightlimit);
  }

  @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
  public Watch getWatchbyId(@PathVariable("id") String id) {
    return WATCH_SERVICE.getWatchById(id);
  }

  @RequestMapping(value = "update/images/{id}", method = RequestMethod.POST)
  public String updateImages(@PathVariable("id") String id, @RequestBody Map<String, List<String>> data) {
    return WATCH_SERVICE.updateImages(id, data.get("urls"));
  }

  @RequestMapping(value = "delete/images/{id}", method = RequestMethod.DELETE)
  public String deleteImages(@PathVariable("id") String id, @RequestBody Map<String, List<String>> data) {
    return WATCH_SERVICE.deleteImages(id, data.get("urls"));
  }

  @RequestMapping(value = "search/keyword", method = RequestMethod.GET)
  public List<Watch> getWatchsBySearchBar(@RequestParam("keyword") String keyword) {
    return WATCH_SERVICE.searchByKeyWord(keyword);
  }

  @RequestMapping(value = "num", method = RequestMethod.GET)
  public Map<String, Object> getWatchNum() {
    return WATCH_SERVICE.getWatchNum();
  }

}
