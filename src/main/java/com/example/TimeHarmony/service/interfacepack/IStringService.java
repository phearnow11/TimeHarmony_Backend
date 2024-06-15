package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

public interface IStringService {
    public List<String> jsonArrToStringList(Object data);

    public String autoGenerateString(int length);

    public List<String> stringSpaceSplit(String s);
}
