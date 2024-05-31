package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Filter;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IWatchService;

@Service
public class WatchService implements IWatchService {

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Watch> getWatchById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchById'");
    }

    @Override
    public List<Watch> getWatchByFilter(Filter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchByFilter'");
    }
}
