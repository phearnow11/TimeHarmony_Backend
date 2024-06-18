package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

public interface IStringService {
    public List<String> jsonArrToStringList(Object data);

    public String autoGenerateString(int length);

    public List<String> stringSpaceSplit(String s);

    public int calculateDistance(String x, String y); 

    public int costOfSubstitution (char a, char b); 

    public int min(int...numbers); 
}
