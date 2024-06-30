package com.example.TimeHarmony.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.TimeHarmony.service.interfacepack.IMapService;
import com.google.common.reflect.TypeToken;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonElement;

@Service
public class MapService implements IMapService {

    @Override
    public Map<String, Integer> convertUsingReflection(Object object) throws IllegalAccessException {
        Map<String, Integer> map = new Hashtable<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), (Integer) field.get(object));
        }

        return map;
    }

    @Override
    public Map<String, Object> convertJsonToObjectGson(JsonElement json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    @Override
    public Map<String, Object> convertStringToMap(List<String> data) {
        Map<String, Object> res = new HashMap<>();
        for (String i : data) {
            String[] string_splitted = i.split("@");
            res.put(string_splitted[0], string_splitted[1]);
        }
        return res;
    }

}
