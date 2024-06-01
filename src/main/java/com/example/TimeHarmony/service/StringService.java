package com.example.TimeHarmony.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.example.TimeHarmony.service.interfacepack.IStringService;

public class StringService implements IStringService {

    @Override
    public List<String> jsonArrToStringList(Object data) {
        String stringdata = data.toString(); 
        
        String[] stringlist = stringdata.split(","); 
        List<String> rs = Arrays.asList(stringlist); 
        return rs; 
    }

}
