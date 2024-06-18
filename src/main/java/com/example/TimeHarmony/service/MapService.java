package com.example.TimeHarmony.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.TimeHarmony.service.interfacepack.IMapService;

@Service
public class MapService implements IMapService {

    @Override
    public Map<String, Integer> convertUsingReflection(Object object) throws IllegalAccessException {
        Map<String, Integer> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), (Integer) field.get(object));
        }

        return map;
    }

}
