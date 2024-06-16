package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.dtos.Filter;
import com.example.TimeHarmony.entity.Watch;

public interface IWatchService {
    public List<Watch> getListWatchDECS();

    public String generateWatchId();

    public List<Watch> getWatchByGender(String gender);

    public List<Watch> getWatchBySeries(String series);

    public List<Watch> getWatchByBrand(String brand);

    public List<Watch> getWatchByStyle(String style);

    public List<Watch> getWatchByFeatures(String features);

    public List<Watch> get30WatchesSortedByCreationDate();

    public List<Watch> loadMoreWatchesSortedByCreationDate(List<Watch> old_watch_list);

    public List<Watch> nextPage(int numpage);

    public List<Watch> getPage01();

    public Watch getWatchById(String id);

    public List<Watch> getWatchesByFilter(Filter filter);

    public List<Watch> getWatchesByPriceRange(float leftlimit, float rightlimit);

    public List<Watch> getWatchesByDialColor(String color);

    public List<Watch> getWatchesByCaseShape(String case_shape);

    public List<Watch> getWatchesByBandType(String band);

    public List<Watch> getWatchesByMovement(String movement);

    public String updateImages(String id, List<String> urls);

    public List<Watch> getWatchesFromWatchID(List<String> ids);
}
