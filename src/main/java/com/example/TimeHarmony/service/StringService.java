package com.example.TimeHarmony.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TimeHarmony.service.interfacepack.IStringService;

@Service
public class StringService implements IStringService {

    @Override
    public List<String> jsonArrToStringList(Object data) {
        String stringdata = data.toString();

        String[] stringlist = stringdata.split(",");
        List<String> rs = Arrays.asList(stringlist);
        return rs;
    }

}
