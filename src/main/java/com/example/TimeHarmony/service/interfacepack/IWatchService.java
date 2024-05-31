package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Watch;

public interface IWatchService {
    public List<Watch> getListWatchDECS();

    public List<Watch> getWatchByGender(String gender);

    public List<Watch> getWatchBySeries(String series);

    public List<Watch> getWatchByBrand(String brand);

    public List<Watch> getWatchByStyle(String style);

    public List<Watch> getWatchByFeatures(String features);

    public List<Watch> get30WatchesSortedByCreationDate();

    public List<Watch> loadMoreWatchesSortedByCreationDate(List<Watch> old_watch_list);
}
