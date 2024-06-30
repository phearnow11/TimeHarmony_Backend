package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.nimbusds.jose.shaded.gson.JsonElement;

public interface IMapService {
    Map<String, Integer> convertUsingReflection(Object object) throws IllegalAccessException;

    Map<String, Object> convertJsonToObjectGson(JsonElement json);

    Map<String, Object> convertStringToMap(List<String> data);
}
