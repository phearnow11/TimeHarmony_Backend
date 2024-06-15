package com.example.TimeHarmony.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Override
    public String autoGenerateString(int length) {
        int leftlimit = 48, rightlimit = 122, stringlength = length;
        String code = "";
        Random random = new Random();
        code = random.ints(leftlimit, rightlimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringlength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return code;
    }

    @Override
    public List<String> stringSpaceSplit(String s) {
        String[] str_split = s.split(" ");
        return Arrays.asList(str_split);
    }

}
