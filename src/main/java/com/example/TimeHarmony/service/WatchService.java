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
            for (String url : urls)
                WATCH_REPOSITORY.saveWatch_Images(id, url);
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
    public String deleteWatch(String id) {
        try {
            WATCH_REPOSITORY.deleteById(id);
            return "Watch deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteWatches(List<String> ids) {
        try {
            WATCH_REPOSITORY.deleteAllById(ids);
            return "Watches deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
