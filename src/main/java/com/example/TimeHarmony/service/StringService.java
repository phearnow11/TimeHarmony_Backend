package com.example.TimeHarmony.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.TimeHarmony.service.interfacepack.IStringService;

@Service
public class StringService implements IStringService {

    @Override
    public List<String> jsonArrToStringList(Object data) {
        String original_data = data.toString();
        String stringdata = "";
        for (int i = 1; i < original_data.length() - 1; i++) {
            if (original_data.charAt(i) == ' ')
                continue;
            stringdata += original_data.charAt(i);
        }

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

    @Override
    public int calculateDistance(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculateDistance(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculateDistance(x, y.substring(1)) + 1;
        int deletion = calculateDistance(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    @Override
    public int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    @Override
    public int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    @Override
    public List<Integer> jsonArrtoIntegerList(Object data) {
        String original_data = data.toString();
        String stringdata = "";
        for (int i = 1; i < original_data.length() - 1; i++) {
            if (original_data.charAt(i) == ' ')
                continue;
            stringdata += original_data.charAt(i);
        }

        String[] stringlist = stringdata.split(",");
        List<Integer> rs = new ArrayList<>();
        for (String i : stringlist) {
            rs.add(Integer.parseInt(i));
        }
        return rs;
    }
}
