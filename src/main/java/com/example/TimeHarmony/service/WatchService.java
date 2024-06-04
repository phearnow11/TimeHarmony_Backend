package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.Filter;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IWatchService;

@Service
public class WatchService implements IWatchService {

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public String generateWatchId() {
        int leftLimit = 48; // '0'
        int rightLimit = 57; // '9'
        int targetStringLength = 5;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchesByPriceRange'");
    }

    @Override
    public List<Watch> getWatchesByDialColor(String color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchesByDialColor'");
    }

    @Override
    public List<Watch> getWatchesByCaseShape(String case_shape) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchesByCaseShape'");
    }

    @Override
    public List<Watch> getWatchesByBandType(String band) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchesByBandType'");
    }

    @Override
    public List<Watch> getWatchesByMovement(String movement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWatchesByMovement'");
    }

}
