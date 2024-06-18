package com.example.TimeHarmony.service.interfacepack;

import java.util.Map;

public interface IMapService {
    Map<String, Integer> convertUsingReflection(Object object) throws IllegalAccessException;
}
